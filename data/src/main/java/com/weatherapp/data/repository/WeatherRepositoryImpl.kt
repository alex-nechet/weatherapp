package com.weatherapp.data.repository

import com.weatherapp.data.converter.toUpcomingWeather
import com.weatherapp.domain.data.WeatherRepository
import com.weatherapp.network.datasource.WeatherRemoteDataSource
import com.weatherapp.domain.model.ContentType
import com.weatherapp.domain.model.ErrorType
import com.weatherapp.domain.model.Place
import com.weatherapp.domain.model.UnitGroup
import com.weatherapp.domain.model.UpcomingWeather
import com.weatherapp.domain.model.Wrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext


class WeatherRepositoryImpl(
    private val remote: WeatherRemoteDataSource,
    private val coroutineContext: CoroutineContext
) : WeatherRepository {

    private val cachedData = MutableStateFlow<Wrapper<UpcomingWeather>?>(null)

    @ExperimentalCoroutinesApi
    override fun getUpcomingWeather(
        forceUpdate: Boolean,
        unitGroup: UnitGroup,
        place: Place,
        contentType: ContentType
    ) = cachedData.flatMapLatest { data ->
        when {
            data == null || forceUpdate -> getRemoteUpcomingWeather(unitGroup, place, contentType)
            else -> flow { emit(data) }
        }
    }

    private fun getRemoteUpcomingWeather(
        unitGroup: UnitGroup,
        place: Place,
        contentType: ContentType
    ): Flow<Wrapper<UpcomingWeather>> = flow<Wrapper<UpcomingWeather>> {
        val placeRequest = when (place) {
            is Place.Address -> place.address
            is Place.Coordinates -> "${place.latitude},${place.longitude}"
        }
        try {
            val result = remote.getUpcomingWeather(
                unitGroup = unitGroup.toString(),
                place = placeRequest,
                contentType = contentType.toString()
            )
            result.onSuccess { data ->
                when (data) {
                    null -> emit(Wrapper.Error(ErrorType.Errors.BadLocationRequest))
                    else -> emit(Wrapper.Success(data.toUpcomingWeather()))
                }
            }.onFailure { exception ->
                emit(Wrapper.Error(ErrorType.Errors.ServerError(exception)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Wrapper.Error(ErrorType.CommonErrors.NETWORK_ERROR))
        }
    }.flowOn(coroutineContext)
}