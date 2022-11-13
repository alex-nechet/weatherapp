package com.weatherapp.domain.usecase

import com.weatherapp.domain.data.WeatherRepository
import com.weatherapp.domain.mapper.toTodayWeather
import com.weatherapp.domain.model.Place
import com.weatherapp.domain.model.State
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry

private const val RETRIES = 2L

class CurrentWeatherUseCase(private val repository: WeatherRepository) {
    fun currentWeatherFor(place: Place) = repository.getUpcomingWeather(
        forceUpdate = true,
        place = place
    )
        .retry(RETRIES)
        .map {
            when (it) {
                is State.Error -> State.Error(it.error)
                is State.Loading -> State.Loading()
                is State.Success -> State.Success(it.data.toTodayWeather())
            }
        }
}