package com.test.weather.com.test.weather.presentation.details.args

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class ForecastDetailsArgs(
    val date: String
) : Parcelable
