package com.test.weather.com.test.weather.data.model.forecast


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(

    @SerialName("current")
    val current: Current?,

    @SerialName("daily")
    val forecast: Forecast
)