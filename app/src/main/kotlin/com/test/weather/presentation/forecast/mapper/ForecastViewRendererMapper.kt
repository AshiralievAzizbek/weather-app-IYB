package com.test.weather.com.test.weather.presentation.forecast.mapper

import com.test.weather.R.string.temperature_template
import com.test.weather.R.string.temperature_change_template
import com.test.weather.com.test.weather.data.local.database.model.ForecastEntity
import com.test.weather.com.test.weather.domain.resourcemanager.api.ResourceManager
import com.test.weather.com.test.weather.presentation.forecast.model.ForecastDayViewRenderer
import com.test.weather.com.test.weather.presentation.forecast.model.ForecastViewRenderer

fun ForecastEntity.toForecastViewRenderer(
    resourceManager: ResourceManager
): ForecastViewRenderer {
    val current = currentTemperature.toString()
    val forecast = forecast.map {
        ForecastDayViewRenderer(
            date = it.date,
            temperature = resourceManager.getString(
                temperature_change_template,
                it.minTemperature.toString(),
                it.maxTemperature.toString()
            )
        )
    }
    return ForecastViewRenderer(
        currentTemperature = resourceManager.getString(temperature_template, current),
        days = forecast
    )
}
