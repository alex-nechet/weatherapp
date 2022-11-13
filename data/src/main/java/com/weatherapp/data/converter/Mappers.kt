package com.weatherapp.data.converter

import com.weatherapp.domain.model.Day
import com.weatherapp.domain.model.PrecipationType
import com.weatherapp.domain.model.UpcomingWeather
import com.weatherapp.network.model.DayResponse
import com.weatherapp.network.model.UpcomingWeatherResponse
import kotlin.math.roundToInt


fun UpcomingWeatherResponse.toUpcomingWeather() = UpcomingWeather(
    latitude = latitude,
    longitude = longitude,
    resolvedAddress = resolvedAddress.orEmpty(),
    address = address.orEmpty(),
    timezone = timezone.orEmpty(),
    description = description.orEmpty(),
    days = days?.map { it.toDay() },
    currentConditions = currentConditions?.toDay()
)

fun DayResponse.toDay() = Day(
    datetime = datetime.orEmpty(),
    tempmax = tempmax?.roundToInt(),
    tempmin = tempmin?.roundToInt(),
    temp = temp?.roundToInt(),
    feelslike = feelslike?.roundToInt(),
    conditions = conditions.orEmpty(),
    description = description.orEmpty(),
    icon = icon.orEmpty(),
)