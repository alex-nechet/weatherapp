package com.weatherapp.domain.model

enum class WeatherPicture {
    CLEAR, CLOUDY, SNOWY, SNOW, RAINY, RAIN, NONE;

    override fun toString() = name.lowercase()
}