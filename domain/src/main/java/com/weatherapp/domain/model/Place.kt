package com.weatherapp.domain.model

sealed interface Place {
    data class Coordinates(val latitude: Double, val longitude: Double) : Place
    data class Address(val address: String) : Place
}
