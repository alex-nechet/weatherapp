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
    val days: List<DayResponse>? = null,
    val currentConditions: DayResponse? = null
)

