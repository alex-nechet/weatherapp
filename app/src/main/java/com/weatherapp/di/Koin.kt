package com.weatherapp.di

import com.weatherapp.data.di.dataModule
import com.weatherapp.network.di.networkModule

object Koin {
    val modules = networkModule + dataModule
}