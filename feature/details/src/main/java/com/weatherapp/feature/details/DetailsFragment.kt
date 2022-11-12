package com.weatherapp.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.extractResource
import com.example.common.hide
import com.example.common.loadImage
import com.example.common.nonNull
import com.example.common.show
import com.weatherapp.domain.model.State
import com.weatherapp.domain.model.TodayWeather
import com.weatherapp.domain.model.UpcomingWeather
import com.weatherapp.feature.details.databinding.FragmentDetailsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null
    private val viewModel by viewModel<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            viewModel.getCurrentWeather()
        }

        lifecycleScope.launch {
            viewModel.state.collectLatest {
                setViews(it)
            }
        }
    }

    private fun setViews(state: State<TodayWeather>) = binding?.let {
        with(it) {
            progress.isVisible = state is State.Loading
            content.isVisible = !progress.isVisible && state !is State.Error
            when (state) {
                is State.Error -> {

                }
                is State.Success -> {
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
                is State.Loading -> {

                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}