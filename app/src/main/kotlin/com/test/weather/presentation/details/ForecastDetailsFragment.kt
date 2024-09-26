package com.test.weather.com.test.weather.presentation.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.weather.R.layout.fragment_forecast_details

class ForecastDetailsFragment : Fragment(fragment_forecast_details) {

    private val viewModel by viewModels<ForecastDetailsViewModel>()

}