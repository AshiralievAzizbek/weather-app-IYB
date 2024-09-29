package com.test.weather.com.test.weather.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.weather.com.test.weather.data.local.database.converter.ForecastEntityConverter
import com.test.weather.com.test.weather.data.local.database.model.ForecastEntity

@Database(
    entities = [ForecastEntity::class],
    version = 1
)
@TypeConverters(ForecastEntityConverter::class)
abstract class AppDatabase : RoomDatabase(), DatabaseProvider
