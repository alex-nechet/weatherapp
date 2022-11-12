package com.weatherapp.domain.model

sealed interface Place {
    data class Coordinates(val latitude: Float, val longitude: Float) : Place
    data class Address(val address: String) : Place
}
