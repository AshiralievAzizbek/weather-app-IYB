package com.test.weather.com.test.weather.data.local.database.mapper

import com.test.weather.com.test.weather.data.local.database.model.Day
import com.test.weather.com.test.weather.data.local.database.model.ForecastEntity
import com.test.weather.com.test.weather.data.local.database.model.Hour
import com.test.weather.com.test.weather.data.remote.model.ForecastDay
import com.test.weather.com.test.weather.data.remote.model.ForecastHour
import com.test.weather.com.test.weather.data.remote.model.ForecastResponse


fun ForecastResponse.toEntity(): ForecastEntity {
    return ForecastEntity(
        currentTemperature = current?.temp ?: 0.0,
        forecast = forecast?.forecastDays?.map { it.toEntity() }.orEmpty()
    )
}

fun ForecastDay?.toEntity(): Day {
    this ?: return Day("", emptyList(), 0.0, 0.0)
    return Day(
        date.orEmpty(),
        hours = hours?.map { it.toEntity() }.orEmpty(),
        maxTemperature = day?.maxTemp ?: 0.0,
        minTemperature = day?.minTemp ?: 0.0
    )
}

fun ForecastHour?.toEntity(): Hour {
    this ?: return Hour(0, 0.0)
    return Hour(time ?: 0, temp ?: 0.0)
}
