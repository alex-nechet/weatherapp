package com.example.common

import androidx.annotation.StringRes
import com.weatherapp.domain.model.ErrorType

@StringRes
fun ErrorType.CommonErrors.extractResources() = when(this){
    ErrorType.CommonErrors.NETWORK_ERROR -> R.string.error_network
}

@StringRes
fun ErrorType.Errors.extractResources() = when(this){
    is ErrorType.Errors.BadLocationRequest -> R.string.error_bad_location_request
    is ErrorType.Errors.ServerError -> null
}