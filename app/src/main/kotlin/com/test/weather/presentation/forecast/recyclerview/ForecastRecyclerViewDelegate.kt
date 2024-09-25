package com.test.weather.com.test.weather.presentation.forecast.delegate

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.test.weather.com.test.weather.presentation.forecast.model.ForecastViewRenderer
import ru.surfstudio.android.easyadapter.EasyAdapter

class ForecastRecyclerViewDelegate(
    layoutInflater: LayoutInflater,
    view: RecyclerView,
    onClick: () -> Unit
) {

    private val easyAdapter = EasyAdapter()
    private val controller = ForecastItemController(layoutInflater, onClick)

    init {
        view.adapter = easyAdapter
    }

    fun setItems(items: List<ForecastViewRenderer>) {
        easyAdapter.setData(items, controller)
    }
}
