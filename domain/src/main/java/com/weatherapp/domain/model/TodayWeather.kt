package com.weatherapp.domain.model

data class TodayWeather(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val resolvedAddress: String,
    val address: String,
    val timezone: String,
    val datetime: String,
    val tempmax: Double? = null,
    val tempmin: Double? = null,
    val temp: Double? = null,
    val feelslike: Double? = null,
    val humidity: Double? = null,
    val precip: Int? = null,
    val preciptype: PrecipationType? = null,
    val windspeed: Double? = null,
    val pressure: Double? = null,
    val uvindex: Int? = null,
    val sunrise: String,
    val sunset: String,
    val conditions: String,
    val description: String,
    val icon: String,
    val hours : List<Hours>? = null
)