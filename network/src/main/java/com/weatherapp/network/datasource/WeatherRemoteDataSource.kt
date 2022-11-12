package com.weatherapp.network.datasource

import com.weatherapp.network.ResponseConverter
import com.weatherapp.network.WeatherApi
import com.weatherapp.network.model.UpcomingWeatherResponse

interface WeatherRemoteDataSource {
    suspend fun getUpcomingWeather(
        unitGroup: String,
        place: String,
        contentType: String
    ): Result<UpcomingWeatherResponse?>
}

class WeatherRemoteDataSourceImpl(
    private val weatherApi: WeatherApi,
    private val responseConverter: ResponseConverter
) : WeatherRemoteDataSource {
    override suspend fun getUpcomingWeather(
        unitGroup: String,
        place: String,
        contentType: String
    ): Result<UpcomingWeatherResponse?> {
        val response = weatherApi.getUpcomingWeather(
            unitGroup = unitGroup,
            place = place,
            contentType = contentType
        )
        return responseConverter.toResult { response }
    }
}