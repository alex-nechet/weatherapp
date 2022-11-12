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

data class Day(
    val datetime: String,
    val tempmax: Double?,
    val tempmin: Double?,
    val temp: Double?,
    val feelslikemax: Double?,
    val feelslikemin: Double?,
    val feelslike: Double?,
    val humidity: Double?,
    val precip: Int?,
    val preciptype: PrecipationType?,
    val windspeed: Double?,
    val pressure: Double?,
    val visibility: Double,
    val uvindex: Int?,
    val sunrise: String,
    val sunset: String,
    val conditions: String,
    val description: String,
    val icon: String,
    val source: String,
    val hours: List<Hours>?,
)

data class Hours(
    val datetime: String,
    val tempmax: Double?,
    val tempmin: Double?,
    val temp: Double?,
    val feelslikemax: Double?,
    val feelslikemin: Double?,
    val feelslike: Double?,
    val humidity: Double?,
    val precip: Int?,
    val preciptype: PrecipationType?,
    val windspeed: Double?,
    val pressure: Double?,
    val visibility: Double,
    val uvindex: Int?,
    val sunrise: String,
    val sunset: String,
    val conditions: String,
    val description: String,
    val icon: String,
    val source: String
)