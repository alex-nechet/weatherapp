package com.weatherapp.data.converter

import com.weatherapp.domain.model.Day
import com.weatherapp.domain.model.Hours
import com.weatherapp.domain.model.PrecipationType
import com.weatherapp.domain.model.TodayWeather
import com.weatherapp.domain.model.UpcomingWeather
import com.weatherapp.network.model.DayResponse
import com.weatherapp.network.model.UpcomingWeatherResponse
import kotlin.math.round
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
    feelslikemax = feelslikemax?.roundToInt(),
    feelslikemin = feelslikemin?.roundToInt(),
    feelslike = feelslike?.roundToInt(),
    humidity = humidity,
    precip = precip,
    preciptype = preciptype?.mapNotNull {
        PrecipationType.values().find { enum -> enum.toString() == it.lowercase() }
    },
    windspeed = windspeed,
    pressure = pressure,
    visibility = visibility ?: 0.0,
    uvindex = uvindex,
    sunrise = sunrise.orEmpty(),
    sunset = sunset.orEmpty(),
    conditions = conditions.orEmpty(),
    description = description.orEmpty(),
    icon = icon.orEmpty(),
    source = source.orEmpty(),
    hours = hours?.map { it.toHours() }
)

fun DayResponse.toHours() = Hours(
    datetime = datetime.orEmpty(),
    tempmax = tempmax,
    tempmin = tempmin,
    temp = temp,
    feelslikemax = feelslikemax,
    feelslikemin = feelslikemin,
    feelslike = feelslike,
    humidity = humidity,
    precip = precip,
    preciptype = preciptype?.mapNotNull {
        PrecipationType.values().find { enum -> enum.toString() == it.lowercase() }
    },
    windspeed = windspeed,
    pressure = pressure,
    visibility = visibility ?: 0.0,
    uvindex = uvindex,
    sunrise = sunrise.orEmpty(),
    sunset = sunset.orEmpty(),
    conditions = conditions.orEmpty(),
    description = description.orEmpty(),
    icon = icon.orEmpty(),
    source = source.orEmpty(),
)