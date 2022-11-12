package com.weatherapp.di

import com.weatherapp.data.di.dataModule
import com.weatherapp.data.di.useCaseModule
import com.weatherapp.feature.details.DetailsViewModel
import com.weatherapp.network.di.networkModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Koin {
    private val presentationModule = module {
        viewModel { DetailsViewModel(get()) }
    }
    val modules = networkModule + dataModule + useCaseModule + presentationModule
}