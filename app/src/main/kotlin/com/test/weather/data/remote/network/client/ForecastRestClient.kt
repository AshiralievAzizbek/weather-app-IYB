package com.test.weather.com.test.weather.data.remote.network.client

import com.github.kittinunf.result.Result
import com.test.weather.com.test.weather.data.remote.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastRestClient {

    @GET("v1/forecast.json")
    suspend fun loadForecast(
        @Query("q") location: String = "Tashkent",
        @Query("days") days: Int = 10,
        @Query("key") key: String = "25ac730b2ce146ea993133310242909"
    ): Result<ForecastResponse, Exception>
}
