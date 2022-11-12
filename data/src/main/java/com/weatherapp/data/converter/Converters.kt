package com.weatherapp.data.converter

import com.weatherapp.domain.model.Day
import com.weatherapp.domain.model.Hours
import com.weatherapp.domain.model.PrecipationType
import com.weatherapp.domain.model.TodayWeather
import com.weatherapp.domain.model.UpcomingWeather
import com.weatherapp.network.model.DayResponse
import com.weatherapp.network.model.UpcomingWeatherResponse


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

fun UpcomingWeather.toTodayWeather() = TodayWeather(
    latitude = latitude,
    longitude = longitude,
    resolvedAddress = resolvedAddress,
    address = address,
    timezone= timezone,
    datetime = currentConditions?.datetime.orEmpty(),
    tempmax = currentConditions?.tempmax,
    tempmin = currentConditions?.tempmin,
    temp = currentConditions?.temp,
    feelslike = currentConditions?.feelslike,
    humidity = currentConditions?.humidity,
    precip = currentConditions?.precip,
    preciptype = currentConditions?.preciptype,
    windspeed = currentConditions?.windspeed,
    pressure = currentConditions?.pressure,
    uvindex = currentConditions?.uvindex,
    sunrise = currentConditions?.sunrise.orEmpty(),
    sunset = currentConditions?.sunset.orEmpty(),
    conditions = currentConditions?.conditions.orEmpty(),
    description = currentConditions?.description.orEmpty(),
    icon = currentConditions?.icon.orEmpty(),
    hours = currentConditions?.hours
)

fun DayResponse.toDay() = Day(
    datetime = datetime.orEmpty(),
    tempmax = tempmax,
    tempmin = tempmin,
    temp = temp,
    feelslikemax = feelslikemax,
    feelslikemin = feelslikemin,
    feelslike = feelslike,
    humidity = humidity,
    precip = precip,
    preciptype = PrecipationType.values().find{
        it.name.lowercase() == preciptype?.lowercase()
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
    hours =  hours?.map { it.toHours() }
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
    preciptype = PrecipationType.values().find{
        it.name.lowercase() == preciptype?.lowercase()
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