package com.test.weather.com.test.weather.data.network.client

import com.test.weather.com.test.weather.data.model.forecast.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastRestClient {

    @GET("v1/forecast")
    suspend fun loadForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min",
        @Query("current") current: String = "temperature_2m",
        @Query("timezone") timezone: String = "GMT",
        @Query("forecast_days") forecastDays: Int = 14,
    ): ForecastResponse
}
