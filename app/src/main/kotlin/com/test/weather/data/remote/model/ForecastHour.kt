package com.test.weather.com.test.weather.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastHour(

    @SerialName("temp_c")
    val temp: Double?,

    @SerialName("time_epoch")
    val time: Long?
)