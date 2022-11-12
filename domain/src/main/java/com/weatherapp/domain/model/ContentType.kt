package com.weatherapp.domain.model

import java.util.Locale

enum class ContentType {
    JSON;

    override fun toString() = name.lowercase(Locale.getDefault())
}