package com.weatherapp.domain.model

enum class UnitGroup(val suffix: String) {
    METRIC("C"), US("F"), UK("C");

    override fun toString() = name.lowercase()
}