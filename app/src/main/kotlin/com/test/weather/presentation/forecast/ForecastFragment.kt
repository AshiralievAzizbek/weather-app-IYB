package com.test.weather.com.test.weather.presentation.forecast

import android.R.layout.simple_spinner_dropdown_item
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.test.weather.R
import com.test.weather.R.layout.fragment_forecast
import com.test.weather.com.test.weather.presentation.details.ForecastDetailsFragment
import com.test.weather.com.test.weather.presentation.details.args.ForecastDetailsArgs
import com.test.weather.com.test.weather.presentation.forecast.recyclerview.ForecastRecyclerViewDelegate
import com.test.weather.com.test.weather.presentation.forecast.utils.OnDaysItemSelectedListener
import com.test.weather.com.test.weather.utils.launchWhenResumed
import dagger.hilt.android.AndroidEntryPoint
import com.test.weather.databinding.FragmentForecastBinding as ViewBinding

@AndroidEntryPoint
class ForecastFragment : Fragment(fragment_forecast) {

    private val viewModel by viewModels<ForecastViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewBinding = ViewBinding.bind(view)
        val rvDelegate = ForecastRecyclerViewDelegate(
            layoutInflater = layoutInflater,
            view = viewBinding.rvForecast
        ) { date ->
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.container_main, ForecastDetailsFragment(ForecastDetailsArgs(date)))
                .commit()
        }

        launchWhenResumed {
            viewModel.viewState.collect {
                viewBinding.tvCurrentTemperature.text = it.currentTemperature
                rvDelegate.setItems(it.forecastList, it.maxDays)
            }
        }

        val adapter = ArrayAdapter(
            requireContext(),
            simple_spinner_dropdown_item,
            arrayOf("3 days", "7 days", "10 days")
        )
        with(viewBinding) {
            forecastDaysCount.setAdapter(adapter)
            forecastDaysCount.onItemSelectedListener = OnDaysItemSelectedListener {
                viewModel.changeForecastMaxDays(it)
            }
        }
    }
}
