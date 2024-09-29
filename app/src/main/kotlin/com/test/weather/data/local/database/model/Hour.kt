package com.test.weather.com.test.weather.data.local.database.model

import kotlinx.serialization.Serializable

@Serializable
class Hour(

    val time: Long,

    val temperature: Double
)