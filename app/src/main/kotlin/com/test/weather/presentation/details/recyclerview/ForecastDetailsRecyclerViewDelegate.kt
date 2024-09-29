package com.test.weather.com.test.weather.presentation.details.recyclerview

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.test.weather.com.test.weather.presentation.details.model.ForecastHourViewRenderer
import com.test.weather.com.test.weather.presentation.forecast.recyclerview.ForecastItemController
import com.test.weather.com.test.weather.presentation.forecast.model.ForecastDayViewRenderer
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.recycler.decorator.Decorator

class ForecastDetailsRecyclerViewDelegate(
    layoutInflater: LayoutInflater,
    view: RecyclerView,
) {

    private val easyAdapter = EasyAdapter()
    private val controller = ForecastDetailsItemController(layoutInflater)

    init {
        val decor = Decorator.Builder()
            .offset(controller.viewType() to ForecastDetailsItemOffsetDecor)
            .build()

        view.adapter = easyAdapter
        view.addItemDecoration(decor)
    }

    fun setItems(items: List<ForecastHourViewRenderer>) {
        easyAdapter.setData(items, controller)
    }
}
