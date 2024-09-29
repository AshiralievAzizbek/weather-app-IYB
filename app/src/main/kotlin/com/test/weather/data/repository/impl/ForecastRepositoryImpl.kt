package com.test.weather.com.test.weather.data.repository.impl

import com.test.weather.com.test.weather.data.local.database.dao.ForecastDao
import com.test.weather.com.test.weather.data.local.database.mapper.toEntity
import com.test.weather.com.test.weather.data.local.database.model.ForecastEntity
import com.test.weather.com.test.weather.data.remote.network.client.ForecastRestClient
import com.test.weather.com.test.weather.data.repository.api.ForecastRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ForecastRepositoryImpl @Inject constructor(
    private val restClient: ForecastRestClient,
    private val forecastDao: ForecastDao
) : ForecastRepository {

    override val forecastFlow: Flow<ForecastEntity?> get() = forecastDao.forecast()

    override suspend fun loadForecast() {
        withContext(Dispatchers.IO) {
            restClient.loadForecast()
        }.fold(
            success = {
                withContext(Dispatchers.IO) {
                    forecastDao.insertForecast(it.toEntity())
                }
            },
            failure = {
                // handle exception
            }
        )
    }
}
