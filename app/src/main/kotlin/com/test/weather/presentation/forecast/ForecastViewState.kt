package com.test.weather.com.test.weather.presentation.forecast

import com.test.weather.com.test.weather.presentation.forecast.model.ForecastDayViewRenderer

data class ForecastViewState(
    val forecastList: List<ForecastDayViewRenderer> = emptyList(),
    val currentTemperature: String = "",
    val maxDays: Int = 3
)
