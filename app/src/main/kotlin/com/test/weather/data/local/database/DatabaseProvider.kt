package com.test.weather.com.test.weather.data.local.database

import com.test.weather.com.test.weather.data.local.database.dao.ForecastDao

interface DatabaseProvider {

    val forecastDao: ForecastDao
}