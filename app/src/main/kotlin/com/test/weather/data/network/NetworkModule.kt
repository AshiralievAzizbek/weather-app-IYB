package com.test.weather.com.test.weather.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.test.weather.com.test.weather.data.network.client.ForecastRestClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
            .build()
    }

    @Provides
    @Singleton
    fun retrofit(client: OkHttpClient): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun forecastRestClient(retrofit: Retrofit): ForecastRestClient {
        return retrofit.create(ForecastRestClient::class.java)
    }
}
