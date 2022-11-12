package com.weatherapp.domain.model

data class UpcomingWeather(
    val latitude: Double?,
    val longitude: Double?,
    val resolvedAddress: String,
    val address: String,
    val timezone: String,
    val description: String,
    val days: List<Day>?,
    val currentConditions: Day?
)



