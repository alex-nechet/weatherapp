package com.weatherapp.feature.details

import android.Manifest
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.dispose
import com.example.common.extractResource
import com.example.common.loadImage
import com.example.common.nonNull
import com.google.android.gms.location.LocationServices
import com.permissionx.guolindev.PermissionX
import com.weatherapp.domain.model.GlobalErrors
import com.weatherapp.domain.model.Place
import com.weatherapp.domain.model.State
import com.weatherapp.domain.model.TodayWeather
import com.weatherapp.feature.details.databinding.FragmentDetailsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!

    private val viewModel by viewModel<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            requestPermissions()
        }

        lifecycleScope.launch {
            viewModel.state.collectLatest {
                setViews(it)
            }
        }
    }

    private fun requestPermissions() {
        requestLocationPermissions(
            grantedAction = { getWeatherForDefinedLocation() },
            deniedAction = { viewModel.showLocationPermissionError() }
        )
    }

    @SuppressWarnings("MissingPermission")
    private fun getWeatherForDefinedLocation() {
        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation.addOnSuccessListener {
            if (it != null) {
                val geocoder = Geocoder(requireContext(), Locale.getDefault());
                val addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                val place = when (addresses) {
                    null -> Place.Coordinates(it.latitude, it.longitude)
                    else -> Place.Address(
                        address = "${addresses[0].countryName}, ${addresses[0].locality}"
                    )
                }
                viewModel.getCurrentWeather(place)
            }
        }
    }

    private fun requestLocationPermissions(
        grantedAction: () -> Unit,
        deniedAction: (List<String>) -> Unit
    ) {
        val permissions = mutableListOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        PermissionX.init(this).permissions(permissions.toList())
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    permissions = deniedList,
                    message = getString(R.string.error_needs_permission),
                    positiveText = getString(R.string.ok),
                    negativeText = getString(R.string.cancel)
                )
            }
            .request { allGranted, _, deniedList ->
                when {
                    allGranted -> grantedAction.invoke()
                    else -> deniedAction.invoke(deniedList)
                }
            }

    }

    private fun setViews(state: State<TodayWeather>) = with(binding) {
        progress.isVisible = state is State.Loading
        content.isVisible = state is State.Success
        errorContainer.isVisible = state is State.Error
        when (state) {
            is State.Error -> handleErrorState(state)
            is State.Success -> {
                setHeaderData(state)
                setFooterData(state)
            }
            is State.Loading -> {
                //no-op
            }
        }
    }

    private fun FragmentDetailsBinding.setFooterData(state: State.Success<TodayWeather>) {
        val adapter = DailyAdapter()
        dailyList.layoutManager = LinearLayoutManager(requireContext())
        dailyList.adapter = adapter
        adapter.submitList(state.data.days)
    }

    private fun FragmentDetailsBinding.setHeaderData(state: State.Success<TodayWeather>) {
        location.text = state.data.resolvedAddress
        status.text = state.data.conditions
        feelsLike.text = getString(R.string.feels_like, state.data.feelslike)
        min.isVisible = state.data.tempmax.nonNull()
        max.isVisible = state.data.tempmax.nonNull()
        max.text = getString(R.string.max, state.data.tempmax)
        min.text = getString(R.string.min, state.data.tempmin)
        currentTemperature.text = state.data.temp
        picture.loadImage(state.data.icon.extractResource())
    }

    private fun FragmentDetailsBinding.handleErrorState(state: State.Error<TodayWeather>) {
        if (state.error is GlobalErrors) {
            val error = state.error as GlobalErrors
            errorText.text = getString(error.extractResource())
            errorActionButton.text = getString(R.string.retry)
            errorActionButton.setOnClickListener {
                when (error) {
                    GlobalErrors.LocationPermissionError -> requestPermissions()
                    else -> getWeatherForDefinedLocation()
                }
            }
        } else {
            //no-op
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.picture.dispose()
        _binding = null
    }
}