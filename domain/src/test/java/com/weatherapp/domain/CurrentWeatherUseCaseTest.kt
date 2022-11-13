package com.weatherapp.domain

import app.cash.turbine.test
import com.weatherapp.domain.data.WeatherRepository
import com.weatherapp.domain.mapper.toTodayWeather
import com.weatherapp.domain.model.ContentType
import com.weatherapp.domain.model.Day
import com.weatherapp.domain.model.GlobalErrors
import com.weatherapp.domain.model.Place
import com.weatherapp.domain.model.State
import com.weatherapp.domain.model.TodayWeather
import com.weatherapp.domain.model.UnitGroup
import com.weatherapp.domain.model.UpcomingWeather
import com.weatherapp.domain.usecase.CurrentWeatherUseCaseImpl
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentWeatherUseCaseTest {

    private val day = Day(
        datetime = "",
        tempmax = 0,
        tempmin = 0,
        temp = 0,
        feelslike = 0,
        conditions = "",
        description = "",
        icon = ""
    )

    private val upcomingWeather = UpcomingWeather(
        latitude = 0.0,
        longitude = 0.0,
        resolvedAddress = "",
        address = "",
        timezone = "",
        description = "",
        days = listOf(day),
        currentConditions = day
    )

    private val mockedWeatherRepositorySuccess = object : WeatherRepository {
        override fun getUpcomingWeather(
            forceUpdate: Boolean,
            unitGroup: UnitGroup,
            place: Place,
            contentType: ContentType
        ): Flow<State<UpcomingWeather>> = flow { emit(State.Success(upcomingWeather)) }
    }

    private val mockedWeatherRepositoryFail = object : WeatherRepository {
        override fun getUpcomingWeather(
            forceUpdate: Boolean,
            unitGroup: UnitGroup,
            place: Place,
            contentType: ContentType
        ): Flow<State<UpcomingWeather>> = flow { emit(State.Error(GlobalErrors.NetworkError)) }
    }

    @Test
    fun testSuccess() = runTest(UnconfinedTestDispatcher()) {
        val place = Place.Address("")
        val unitGroup = UnitGroup.METRIC
        val useCase = CurrentWeatherUseCaseImpl(mockedWeatherRepositorySuccess)
        useCase.currentWeatherFor(place, unitGroup).test {
            assertEquals(awaitItem(), State.Success(upcomingWeather.toTodayWeather()))
            awaitComplete()
        }
    }

    @Test
    fun testFail() = runTest(UnconfinedTestDispatcher()) {
        val place = Place.Address("")
        val unitGroup = UnitGroup.METRIC
        val useCase = CurrentWeatherUseCaseImpl(mockedWeatherRepositoryFail)
        useCase.currentWeatherFor(place, unitGroup).test {
            assertEquals(awaitItem(), State.Error<TodayWeather>(GlobalErrors.NetworkError))
            awaitComplete()
        }
    }
}