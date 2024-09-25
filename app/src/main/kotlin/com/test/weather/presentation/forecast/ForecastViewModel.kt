package com.test.weather.com.test.weather.presentation.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.weather.R.string.temperature_template
import com.test.weather.com.test.weather.data.repository.api.ForecastRepository
import com.test.weather.com.test.weather.domain.resourcemanager.api.ResourceManager
import com.test.weather.com.test.weather.presentation.forecast.mapper.toForecastViewRendererList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.test.weather.com.test.weather.presentation.forecast.ForecastViewState as ViewState

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val repository: ForecastRepository,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val mutableViewState = MutableStateFlow(ViewState())
    val viewState = mutableViewState.asStateFlow()

    init {
        loadForecast()
    }

    private fun loadForecast() = viewModelScope.launch {
        repository.loadForecast().fold(
            success = {
                val forecastList = it.forecast.toForecastViewRendererList(resourceManager)
                val temperature = it.current?.temperature ?: 0.0
                mutableViewState.emit(
                    viewState.value.copy(
                        forecastList = forecastList,
                        currentTemperature = resourceManager.getString(
                            temperature_template,
                            temperature.toString()
                        )
                    )
                )
            },
            failure = {
                // handle httpException
            }
        )
    }
}