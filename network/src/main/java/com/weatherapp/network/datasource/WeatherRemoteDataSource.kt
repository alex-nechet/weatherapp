package com.weatherapp.network.datasource

import com.weatherapp.network.ResponseConverter
import com.weatherapp.network.WeatherApi
import com.weatherapp.network.model.ContentType
import com.weatherapp.network.model.UnitGroup
import com.weatherapp.network.model.UpcomingWeatherResponse

interface WeatherRemoteDataSource {
}

class WeatherRemoteDataSourceImpl(
    private val weatherApi: WeatherApi,
    private val responseConverter: ResponseConverter
) : WeatherRemoteDataSource {

    suspend fun getUpcomingWeather(
        unitGroup: UnitGroup = UnitGroup.METRIC,
        place: String,
        contentType: ContentType = ContentType.JSON
    ): Result<UpcomingWeatherResponse?> {
        val response = weatherApi.getUpcomingWeather(
            unitGroup = unitGroup.toString(),
            place = place,
            contentType = contentType.toString()
        )
        return responseConverter.toResult { response }
    }
}