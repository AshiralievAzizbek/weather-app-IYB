package com.test.weather.com.test.weather.presentation.forecast

import com.test.weather.com.test.weather.presentation.forecast.model.ForecastViewRenderer

data class ForecastViewState(
    val forecastList: List<ForecastViewRenderer> = emptyList(),
    val currentTemperature: String = ""
)