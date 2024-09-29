package com.test.weather.com.test.weather.data.local.database.model

import kotlinx.serialization.Serializable

@Serializable
class Day(

    val date: String,

    val hours: List<Hour>,

    val maxTemperature: Double,

    val minTemperature: Double
)