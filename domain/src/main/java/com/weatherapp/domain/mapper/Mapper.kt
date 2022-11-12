package com.weatherapp.domain.mapper

import com.weatherapp.domain.model.TodayWeather
import com.weatherapp.domain.model.UpcomingWeather

fun UpcomingWeather.toTodayWeather() = TodayWeather(
    latitude = latitude.toString(),
    longitude = longitude.toString(),
    resolvedAddress = resolvedAddress,
    address = address,
    timezone = timezone,
    datetime = currentConditions?.datetime.orEmpty(),
    tempmax = currentConditions?.tempmax.toString(),
    tempmin = currentConditions?.tempmin.toString(),
    temp = currentConditions?.temp.toString(),
    feelslike = currentConditions?.feelslike.toString(),
    humidity = currentConditions?.humidity.toString(),
    precip = currentConditions?.precip.toString(),
    preciptype = currentConditions?.preciptype,
    windspeed = currentConditions?.windspeed.toString(),
    pressure = currentConditions?.pressure.toString(),
    uvindex = currentConditions?.uvindex.toString(),
    sunrise = currentConditions?.sunrise.orEmpty(),
    sunset = currentConditions?.sunset.orEmpty(),
    conditions = currentConditions?.conditions.orEmpty(),
    description = currentConditions?.description.orEmpty(),
    icon = currentConditions?.icon.orEmpty(),
    hours = currentConditions?.hours
)