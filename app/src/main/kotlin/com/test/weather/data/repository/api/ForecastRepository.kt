package com.test.weather.com.test.weather.data.repository.api

import com.github.kittinunf.result.Result
import com.test.weather.com.test.weather.data.model.forecast.ForecastResponse
import retrofit2.HttpException

interface ForecastRepository {

    suspend fun loadForecast(): Result<ForecastResponse, HttpException>
}