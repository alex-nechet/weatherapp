package com.weatherapp.network

import com.weatherapp.network.model.UpcomingWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("timeline/{place}")
    suspend fun getUpcomingWeather(
        @Path("place") place: String,
        @Query("unitGroup") unitGroup: String,
        @Query("contentType") contentType: String
    ): Response<UpcomingWeatherResponse>
}