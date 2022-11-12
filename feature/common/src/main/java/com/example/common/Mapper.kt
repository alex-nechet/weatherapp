package com.example.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.weatherapp.domain.model.ErrorType
import com.weatherapp.domain.model.WeatherPicture

@DrawableRes
fun WeatherPicture.extractResource() = when(this){
    WeatherPicture.CLEAR -> R.drawable.sunny
    WeatherPicture.CLOUDY -> R.drawable.cloudy
    WeatherPicture.SNOWY, WeatherPicture.SNOW -> R.drawable.snowy
    WeatherPicture.RAINY, WeatherPicture.RAIN -> R.drawable.rainy
    WeatherPicture.NONE -> null
}

@StringRes
fun ErrorType.CommonErrors.extractResources() = when(this){
    ErrorType.CommonErrors.NETWORK_ERROR -> R.string.error_network
}

@StringRes
fun ErrorType.Errors.extractResources() = when(this){
    is ErrorType.Errors.BadLocationRequest -> R.string.error_bad_location_request
    is ErrorType.Errors.ServerError -> null
}