package com.test.weather.com.test.weather.presentation.forecast.mapper

import com.test.weather.R.string.temperature_change_template
import com.test.weather.com.test.weather.domain.resourcemanager.api.ResourceManager
import com.test.weather.com.test.weather.presentation.forecast.model.ForecastViewRenderer
import com.test.weather.com.test.weather.data.model.forecast.Forecast

fun Forecast.toForecastViewRendererList(
    resourceManager: ResourceManager
): List<ForecastViewRenderer> {
    val forecastList = dateList?.mapIndexed { index, date ->
        val start = minTemperatureList?.getOrNull(index) ?: 0.0
        val end = maxTemperatureList?.getOrNull(index) ?: 0.0
        ForecastViewRenderer(
            date = date.orEmpty(),
            temperature = resourceManager.getString(
                temperature_change_template,
                start.toString(),
                end.toString()
            )
        )
    }
    return forecastList.orEmpty()
}
