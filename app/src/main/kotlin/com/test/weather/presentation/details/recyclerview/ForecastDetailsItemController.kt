package com.test.weather.com.test.weather.presentation.details.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.weather.com.test.weather.presentation.details.model.ForecastHourViewRenderer
import com.test.weather.com.test.weather.presentation.details.recyclerview.ForecastDetailsItemController.ViewHolder
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import com.test.weather.databinding.ForecastDetailsItemViewHolderBinding as ViewBinding

class ForecastDetailsItemController(
    private val layoutInflater: LayoutInflater
) : BindableItemController<ForecastHourViewRenderer, ViewHolder>() {

    inner class ViewHolder(private val binding: ViewBinding) :
        BindableViewHolder<ForecastHourViewRenderer>(binding.root) {
        override fun bind(data: ForecastHourViewRenderer) = with(binding) {
            tvTime.text = data.time
            tvTemperature.text = data.temperature
        }
    }

    override fun createViewHolder(parent: ViewGroup?): ViewHolder {
        val binding = ViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemId(data: ForecastHourViewRenderer) = data
}