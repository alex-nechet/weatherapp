package com.weatherapp.domain.model

sealed class State<T> {
    class Loading<T> : State<T>()
    data class Success<T>(val data: T) : State<T>()
    data class Error<T>(val error: ErrorType) : State<T>()
}