package com.weatherapp.domain.model

sealed interface Wrapper<T> {
    data class Success<T>(val data: T) : Wrapper<T>
    data class Error<T>(val error: ErrorType) : Wrapper<T>
}