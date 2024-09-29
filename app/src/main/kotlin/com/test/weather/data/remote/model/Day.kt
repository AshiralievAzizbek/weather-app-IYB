package com.test.weather.com.test.weather.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Day(

    @SerialName("maxtemp_c")
    val maxTemp: Double?,

    @SerialName("mintemp_c")
    val minTemp: Double?
)