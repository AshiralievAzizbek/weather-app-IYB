package com.test.weather.com.test.weather.presentation.forecast.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.weather.com.test.weather.presentation.forecast.model.ForecastDayViewRenderer
import com.test.weather.com.test.weather.presentation.forecast.recyclerview.ForecastItemController.ViewHolder
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import com.test.weather.databinding.ForecastItemViewHolderBinding as ViewBinding

class ForecastItemController(
    private val layoutInflater: LayoutInflater,
    private val onClick: (String) -> Unit
) : BindableItemController<ForecastDayViewRenderer, ViewHolder>() {

    inner class ViewHolder(private val binding: ViewBinding) :
        BindableViewHolder<ForecastDayViewRenderer>(binding.root) {
        override fun bind(data: ForecastDayViewRenderer) = with(binding) {
            tvDate.text = data.date
            tvTemperature.text = data.temperature
            root.setOnClickListener { onClick.invoke(data.date) }
        }
    }

    override fun createViewHolder(parent: ViewGroup?): ViewHolder {
        val binding = ViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemId(data: ForecastDayViewRenderer) = data
}
