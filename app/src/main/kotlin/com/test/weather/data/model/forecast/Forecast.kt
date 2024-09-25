package com.test.weather.com.test.weather.data.model.forecast


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Forecast(

    @SerialName("temperature_2m_max")
    val maxTemperatureList: List<Double?>?,

    @SerialName("temperature_2m_min")
    val minTemperatureList: List<Double?>?,

    @SerialName("time")
    val dateList: List<String?>?
)
