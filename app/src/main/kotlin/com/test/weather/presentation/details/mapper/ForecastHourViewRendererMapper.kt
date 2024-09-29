package com.test.weather.com.test.weather.presentation.details.mapper

import com.test.weather.R.string.temperature_template
import com.test.weather.com.test.weather.data.local.database.model.Hour
import com.test.weather.com.test.weather.domain.resourcemanager.api.ResourceManager
import com.test.weather.com.test.weather.presentation.details.model.ForecastHourViewRenderer
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val sdf = SimpleDateFormat("HH:mm", Locale.ROOT)

fun Hour.toForecastViewRendererList(
    resourceManager: ResourceManager
): ForecastHourViewRenderer {
    return ForecastHourViewRenderer(
        time = sdf.format(Date(time*1000)),
        temperature = resourceManager.getString(temperature_template, temperature)
    )
}
