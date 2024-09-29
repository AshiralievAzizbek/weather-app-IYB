package com.test.weather.com.test.weather.presentation.forecast.utils

import android.view.View
import android.widget.AdapterView

class OnDaysItemSelectedListener(
    private val onSelected: (Int) -> Unit
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onSelected(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}