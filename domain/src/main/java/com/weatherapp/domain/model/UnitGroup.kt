package com.weatherapp.domain.model

import java.util.Locale

enum class UnitGroup {
    METRIC, US, UK;

    override fun toString() = name.lowercase(Locale.getDefault())
}