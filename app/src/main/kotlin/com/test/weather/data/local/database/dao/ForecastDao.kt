package com.test.weather.com.test.weather.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.test.weather.com.test.weather.data.local.database.model.ForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDao {

    @Query("SELECT * FROM forecast WHERE `key` = 0")
    fun forecast(): Flow<ForecastEntity?>

    @Insert(entity = ForecastEntity::class, onConflict = REPLACE)
    fun insertForecast(forecastEntity: ForecastEntity)
}
