package com.weatherapp.data

import android.accounts.NetworkErrorException
import app.cash.turbine.test
import com.weatherapp.data.converter.toUpcomingWeather
import com.weatherapp.data.repository.WeatherRepositoryImpl
import com.weatherapp.domain.model.ContentType
import com.weatherapp.domain.model.GlobalErrors
import com.weatherapp.domain.model.Place
import com.weatherapp.domain.model.State
import com.weatherapp.domain.model.UnitGroup
import com.weatherapp.domain.model.UpcomingWeather
import com.weatherapp.network.datasource.WeatherRemoteDataSource
import com.weatherapp.network.model.UpcomingWeatherResponse
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryTest {
    private val dispatcher = UnconfinedTestDispatcher()
    private val unitGroup = UnitGroup.UK
    private val place = Place.Address("place")
    private val contentType = ContentType.JSON
    private val response = UpcomingWeatherResponse()
    private val networkErrorException = NetworkErrorException("")

    private val mockedRemote = object : WeatherRemoteDataSource {
        override suspend fun getUpcomingWeather(
            unitGroup: String,
            place: String,
            contentType: String
        ): Result<UpcomingWeatherResponse?> = Result.success(response)
    }

    private val mockedRemoteFail = object : WeatherRemoteDataSource {
        override suspend fun getUpcomingWeather(
            unitGroup: String,
            place: String,
            contentType: String
        ): Result<UpcomingWeatherResponse?> = Result.failure(networkErrorException)
    }

    @Test
    fun testSuccess() = runBlocking (dispatcher) {
        val weatherRepository = WeatherRepositoryImpl(mockedRemote, dispatcher)
        weatherRepository.getUpcomingWeather(
            forceUpdate = false,
            unitGroup = unitGroup,
            place = place,
            contentType = contentType
        ).test {
            assert(awaitItem() is State.Loading)
            assertEquals(awaitItem(), State.Success(response.toUpcomingWeather()))
        }
    }

    @Test
    fun testFail() = runBlocking (dispatcher) {
        val weatherRepository = WeatherRepositoryImpl(mockedRemoteFail, dispatcher)
        weatherRepository.getUpcomingWeather(
            forceUpdate = false,
            unitGroup = unitGroup,
            place = place,
            contentType = contentType
        ).test {
            assert(awaitItem() is State.Loading)
            assertEquals(
                awaitItem(),
                State.Error<UpcomingWeather>(GlobalErrors.ServerError(networkErrorException))
            )
        }
    }
}