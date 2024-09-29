package com.test.weather.com.test.weather.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
class ForecastEntity(

    @PrimaryKey
    val key: Int = 0,

    val currentTemperature: Double,

    val forecast: List<Day>,
)