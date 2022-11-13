package com.example.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.weatherapp.domain.model.ErrorType
import com.weatherapp.domain.model.GlobalErrors
import com.weatherapp.domain.model.WeatherPicture

@DrawableRes
fun WeatherPicture.extractResource() = when(this){
    WeatherPicture.CLEAR -> R.drawable.sunny
    WeatherPicture.CLOUDY -> R.drawable.cloudy
    WeatherPicture.SNOWY, WeatherPicture.SNOW -> R.drawable.snowy
    WeatherPicture.RAINY, WeatherPicture.RAIN -> R.drawable.rainy
    WeatherPicture.NONE -> 0
}

@StringRes
fun GlobalErrors.extractResource() = when(this){
    is GlobalErrors.BadLocationRequest -> R.string.error_bad_location_request
    is GlobalErrors.LocationPermissionError -> R.string.error_permissions_missed
    is GlobalErrors.NetworkError -> R.string.error_network
    is GlobalErrors.ServerError -> 0
}