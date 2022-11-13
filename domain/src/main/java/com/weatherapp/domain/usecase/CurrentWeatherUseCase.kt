package com.weatherapp.domain.usecase

import com.weatherapp.domain.data.WeatherRepository
import com.weatherapp.domain.mapper.toTodayWeather
import com.weatherapp.domain.model.Place
import com.weatherapp.domain.model.State
import com.weatherapp.domain.model.TodayWeather
import com.weatherapp.domain.model.UnitGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry

private const val RETRIES = 2L

interface CurrentWeatherUseCase{

    fun currentWeatherFor(place: Place, unitGroup: UnitGroup): Flow<State<TodayWeather>>
}

class CurrentWeatherUseCaseImpl(private val repository: WeatherRepository) : CurrentWeatherUseCase {
    override fun currentWeatherFor(place: Place, unitGroup : UnitGroup) = repository.getUpcomingWeather(
        forceUpdate = true,
        place = place,
        unitGroup = unitGroup
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