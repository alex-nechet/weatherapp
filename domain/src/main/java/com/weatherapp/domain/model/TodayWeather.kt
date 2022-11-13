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
    val conditions: String,
    val description: String,
    val icon: WeatherPicture,
    val days: List<Day>?
)