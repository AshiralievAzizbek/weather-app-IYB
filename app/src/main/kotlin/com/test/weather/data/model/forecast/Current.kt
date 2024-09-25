package com.test.weather.com.test.weather.data.model.forecast


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Current(

    @SerialName("temperature_2m")
    val temperature: Double?,
)