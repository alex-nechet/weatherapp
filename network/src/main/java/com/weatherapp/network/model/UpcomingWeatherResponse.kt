package com.weatherapp.network.model

data class UpcomingWeatherResponse(
    val queryCost: Int? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val resolvedAddress: String? = null,
    val address: String? = null,
    val timezone: String? = null,
    val tzoffset: Int? = null,
    val description: String? = null,
    val days: List<Day>? = null,
    val currentConditions: Day? = null
)

data class Day(
    val datetime: String? = null,
    val datetimeEpoch: Long? = null,
    val tempmax: Double? = null,
    val tempmin: Double? = null,
    val temp: Double? = null,
    val feelslikemax: Int? = null,
    val feelslikemin: Int? = null,
    val feelslike: Double? = null,
    val dew: Double? = null,
    val humidity: Double? = null,
    val precip: Int? = null,
    val precipprob: Int? = null,
    val precipcover: Int? = null,
    val preciptype: Any? = null,
    val snow: Long? = null,
    val snowdepth: Int? = null,
    val windgust: Int? = null,
    val windspeed: Double? = null,
    val winddir: Double? = null,
    val pressure: Double? = null,
    val cloudcover: Double? = null,
    val visibility: Double? = null,
    val uvindex: Int? = null,
    val sunrise: String? = null,
    val sunriseEpoch: Long? = null,
    val sunset: String? = null,
    val sunsetEpoch: Long? = null,
    val moonphase: Double? = null,
    val conditions: String? = null,
    val description: String? = null,
    val icon: String? = null,
    val source: String? = null,
    val hours: List<Day>? = null,
    val precipsum: Long? = null,
    val precipsumnormal: Long? = null,
    val snowsum: Long? = null,
    val datetimeInstance: String? = null
)