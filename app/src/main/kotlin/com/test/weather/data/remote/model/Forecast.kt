package com.test.weather.com.test.weather.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(

    @SerialName("forecastday")
    val forecastDays: List<ForecastDay?>?
)