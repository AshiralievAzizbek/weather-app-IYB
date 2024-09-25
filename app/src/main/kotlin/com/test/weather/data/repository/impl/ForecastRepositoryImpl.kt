package com.test.weather.com.test.weather.data.repository.impl

import com.github.kittinunf.result.Result
import com.test.weather.com.test.weather.data.network.client.ForecastRestClient
import com.test.weather.com.test.weather.data.repository.api.ForecastRepository
import com.test.weather.com.test.weather.data.model.forecast.ForecastResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

@ViewModelScoped
class ForecastRepositoryImpl @Inject constructor(
    private val restClient: ForecastRestClient
) : ForecastRepository {
    override suspend fun loadForecast(): Result<ForecastResponse, HttpException> {
        return Result.of {
            withContext(Dispatchers.IO) {
                restClient.loadForecast(41.2647, 69.2163) // hardcoded Tashkent location
            }
        }
    }
}
