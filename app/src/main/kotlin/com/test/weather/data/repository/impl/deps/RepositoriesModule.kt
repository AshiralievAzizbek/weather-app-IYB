package com.test.weather.com.test.weather.data.repository.impl.deps

import com.test.weather.com.test.weather.data.repository.api.ForecastRepository
import com.test.weather.com.test.weather.data.repository.impl.ForecastRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoriesModule {

    @Binds
    @ViewModelScoped
    fun forecastRepository(impl: ForecastRepositoryImpl): ForecastRepository
}
