package com.test.weather.com.test.weather.presentation.forecast.model

data class ForecastViewState(
    val currentTemperature: String,
    val forecastList: List<ForecastViewRenderer>
)