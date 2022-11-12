package com.weatherapp.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.hide
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
            when (state) {
                is State.Error -> TODO()
                is State.Success -> {
                    temperature.text = state.data.temp
                    picture.text = state.data.icon
                    content.show()
                    progress.hide()
                }
                is State.Loading ->{
                    content.hide()
                    progress.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}