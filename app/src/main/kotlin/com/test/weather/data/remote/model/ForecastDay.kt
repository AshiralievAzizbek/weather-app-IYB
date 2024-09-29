package com.test.weather.com.test.weather.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(

    @SerialName("date")
    val date: String?,

    @SerialName("day")
    val day: Day?,

    @SerialName("hour")
    val hours: List<ForecastHour?>?
)