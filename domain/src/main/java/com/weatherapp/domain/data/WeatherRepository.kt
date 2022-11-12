package com.weatherapp.domain.data

import com.weatherapp.domain.model.ContentType
import com.weatherapp.domain.model.Place
import com.weatherapp.domain.model.UnitGroup
import com.weatherapp.domain.model.UpcomingWeather
import com.weatherapp.domain.model.Wrapper
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getUpcomingWeather(
        unitGroup: UnitGroup = UnitGroup.METRIC,
        place: Place,
        contentType: ContentType = ContentType.JSON
    ): Flow<Wrapper<UpcomingWeather>>
}