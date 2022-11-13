package com.weatherapp.data.di

import com.weatherapp.data.repository.WeatherRepositoryImpl
import com.weatherapp.domain.data.WeatherRepository
import com.weatherapp.domain.usecase.CurrentWeatherUseCase
import com.weatherapp.domain.usecase.CurrentWeatherUseCaseImpl
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

private const val IO = "inputOutput"

val dataModule = module {
    single<CoroutineContext>(named(IO)) { Dispatchers.IO }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(named(IO))) }
}

val useCaseModule = module {
    single<CurrentWeatherUseCase> { CurrentWeatherUseCaseImpl(get()) }
}