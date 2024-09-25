package com.test.weather.com.test.weather.domain.resourcemanager.impl

import android.content.Context
import com.test.weather.com.test.weather.domain.resourcemanager.api.ResourceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceManager {
    override fun getString(resId: Int): String {
        return context.resources.getString(resId)
    }

    override fun getString(resId: Int, vararg formatArgs: Any): String {
        return context.resources.getString(resId, *formatArgs)
    }
}