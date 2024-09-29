package com.test.weather.com.test.weather.data.local.database

import android.content.Context
import androidx.room.Room
import com.test.weather.com.test.weather.data.local.database.dao.ForecastDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun database(@ApplicationContext context: Context): DatabaseProvider {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    @Provides
    @Singleton
    fun forecastDao(database: DatabaseProvider): ForecastDao {
        return database.forecastDao
    }
}