package com.weatherapp.network.model

import java.util.*

enum class UnitGroup {
    METRIC, US, UK;

    override fun toString() = name.lowercase(Locale.getDefault())
}