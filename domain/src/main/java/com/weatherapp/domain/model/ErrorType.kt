package com.weatherapp.domain.model

sealed interface ErrorType {
    enum class CommonErrors : ErrorType {
        NETWORK_ERROR
    }
    
    sealed interface Errors : ErrorType{
        data class ServerError(val serverError: Throwable) : Errors
        object BadLocationRequest : Errors
    }
}