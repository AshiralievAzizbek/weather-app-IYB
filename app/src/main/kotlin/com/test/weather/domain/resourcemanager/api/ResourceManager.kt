package com.test.weather.com.test.weather.domain.resourcemanager.api

import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String
}