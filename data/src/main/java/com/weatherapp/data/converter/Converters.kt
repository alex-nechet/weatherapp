package com.weatherapp.data.converter

import com.weatherapp.domain.model.UpcomingWeather
import com.weatherapp.network.model.UpcomingWeatherResponse


fun UpcomingWeatherResponse.toUpcomingWeather() = UpcomingWeather()