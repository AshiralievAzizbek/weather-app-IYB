package com.test.weather.com.test.weather.data.repository.api

import com.test.weather.com.test.weather.data.local.database.model.ForecastEntity
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    val forecastFlow: Flow<ForecastEntity?>

    suspend fun loadForecast()
}