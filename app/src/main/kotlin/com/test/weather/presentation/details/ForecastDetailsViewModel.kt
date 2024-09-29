package com.test.weather.com.test.weather.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.weather.com.test.weather.data.repository.api.ForecastRepository
import com.test.weather.com.test.weather.domain.resourcemanager.api.ResourceManager
import com.test.weather.com.test.weather.presentation.details.mapper.toForecastViewRendererList
import com.test.weather.com.test.weather.presentation.forecast.ForecastViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ForecastDetailsViewModel @Inject constructor(
    private val repository: ForecastRepository,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val mutableViewState = MutableStateFlow(ForecastDetailsViewState(emptyList()))
    val viewState = mutableViewState.asStateFlow()

    fun loadDetails(date: String?) = viewModelScope.launch(Dispatchers.Default) {
        date ?: return@launch

        val day = withContext(Dispatchers.IO) {
            repository.forecastFlow.stateIn(viewModelScope)
        }.value?.forecast?.find { it.date == date }

        val hours = day?.hours?.map {
            it.toForecastViewRendererList(resourceManager)
        } ?: emptyList()

        val state = viewState.value.copy(hours = hours)
        mutableViewState.emit(state)
    }
}
