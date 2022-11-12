package com.weatherapp.network.model

import java.util.*

enum class ContentType {
    JSON;

    override fun toString() = name.lowercase(Locale.getDefault())

}