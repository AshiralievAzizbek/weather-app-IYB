package com.test.weather.com.test.weather.domain.resourcemanager

import com.test.weather.com.test.weather.domain.resourcemanager.api.ResourceManager
import com.test.weather.com.test.weather.domain.resourcemanager.impl.ResourceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ResourceManagerModule {

    @Binds
    @Singleton
    fun resourceManager(impl: ResourceManagerImpl): ResourceManager
}
