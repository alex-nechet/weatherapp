package com.weatherapp.domain.model

data class Day(
    val datetime: String,
    val tempmax: Int?,
    val tempmin: Int?,
    val temp: Int?,
    val feelslike: Int?,
    val conditions: String,
    val description: String,
    val icon: String
)