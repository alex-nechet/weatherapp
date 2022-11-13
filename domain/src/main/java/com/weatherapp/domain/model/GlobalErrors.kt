package com.weatherapp.domain.model

sealed class GlobalErrors : ErrorType{
        data class ServerError(val serverError: Throwable) : GlobalErrors()
        object BadLocationRequest : GlobalErrors()
        object NetworkError: GlobalErrors()
        object LocationPermissionError : GlobalErrors()
    }