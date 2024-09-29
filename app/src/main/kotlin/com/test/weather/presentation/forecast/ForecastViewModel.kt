package com.test.weather.com.test.weather.presentation.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.weather.com.test.weather.data.repository.api.ForecastRepository
import com.test.weather.com.test.weather.domain.resourcemanager.api.ResourceManager
import com.test.weather.com.test.weather.presentation.forecast.mapper.toForecastViewRenderer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
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
        subscribeToDatabase()
    }

    private fun subscribeToDatabase() {
        repository.forecastFlow.onEach {
            it ?: return@onEach
            val viewRenderer = it.toForecastViewRenderer(resourceManager)
            val newState = viewState.value.copy(
                forecastList = viewRenderer.days,
                currentTemperature = viewRenderer.currentTemperature
            )
            mutableViewState.emit(newState)
        }.launchIn(viewModelScope)
    }

    private fun loadForecast() = viewModelScope.launch {
        repository.loadForecast()
    }

    fun changeForecastMaxDays(position: Int) = viewModelScope.launch {
        val max = when (position) {
            0 -> THREE_DAYS
            1 -> SEVEN_DAYS
            else -> TEN_DAYS
        }
        val newState = viewState.value.copy(maxDays = max)
        mutableViewState.emit(newState)
    }

    private companion object {
        const val THREE_DAYS = 3
        const val SEVEN_DAYS = 7
        const val TEN_DAYS = 10
    }
}