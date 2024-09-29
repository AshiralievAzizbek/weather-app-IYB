package com.test.weather.com.test.weather.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(

    @SerialName("current")
    val current: Current?,

    @SerialName("forecast")
    val forecast: Forecast?,
)