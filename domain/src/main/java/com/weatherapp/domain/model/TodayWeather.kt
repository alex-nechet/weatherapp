package com.weatherapp.domain.model

data class TodayWeather(
    val latitude: String,
    val longitude: String,
    val resolvedAddress: String,
    val address: String,
    val timezone: String,
    val datetime: String,
    val tempmax: String,
    val tempmin: String,
    val temp: String,
    val feelslike: String,
    val humidity: String,
    val precip: String,
    val preciptype: List<PrecipationType>? = null,
    val windspeed: String,
    val pressure: String,
    val uvindex: String,
    val sunrise: String,
    val sunset: String,
    val conditions: String,
    val description: String,
    val icon: String,
    val hours: List<Hours>? = null
)