package com.test.weather.com.test.weather.presentation.forecast.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.weather.com.test.weather.presentation.forecast.delegate.ForecastItemController.ViewHolder
import com.test.weather.com.test.weather.presentation.forecast.model.ForecastViewRenderer
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import com.test.weather.databinding.ForecastItemViewHolderBinding as ViewBinding

class ForecastItemController(
    private val layoutInflater: LayoutInflater,
    private val onClick: () -> Unit
) : BindableItemController<ForecastViewRenderer, ViewHolder>() {

    inner class ViewHolder(private val binding: ViewBinding) :
        BindableViewHolder<ForecastViewRenderer>(binding.root) {
        override fun bind(data: ForecastViewRenderer) = with(binding) {
            tvDate.text = data.date
            tvTemperature.text = data.temperature
            root.setOnClickListener { onClick.invoke() }
        }
    }

    override fun createViewHolder(parent: ViewGroup?): ViewHolder {
        val binding = ViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemId(data: ForecastViewRenderer) = data
}
