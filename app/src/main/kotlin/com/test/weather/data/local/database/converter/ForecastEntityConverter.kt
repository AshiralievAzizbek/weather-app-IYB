package com.test.weather.com.test.weather.data.local.database.converter

import androidx.room.TypeConverter
import com.test.weather.com.test.weather.data.local.database.model.Day
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ForecastEntityConverter {

    @TypeConverter
    fun fromListOfDays(value: List<Day>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toListOfDays(value: String): List<Day> {
        return Json.decodeFromString(value)
    }
}