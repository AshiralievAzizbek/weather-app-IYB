package com.test.weather.com.test.weather.presentation.forecast.recyclerview

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.test.weather.com.test.weather.presentation.forecast.delegate.ForecastItemController
import com.test.weather.com.test.weather.presentation.forecast.model.ForecastViewRenderer
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.recycler.decorator.Decorator

class ForecastRecyclerViewDelegate(
    layoutInflater: LayoutInflater,
    view: RecyclerView,
    onClick: (date: String) -> Unit
) {

    private val easyAdapter = EasyAdapter()
    private val controller = ForecastItemController(layoutInflater, onClick)

    init {
        val decor = Decorator.Builder()
            .offset(controller.viewType() to ForecastItemOffsetDecor)
            .build()

        view.adapter = easyAdapter
        view.addItemDecoration(decor)
    }

    fun setItems(items: List<ForecastViewRenderer>) {
        easyAdapter.setData(items, controller)
    }
}
