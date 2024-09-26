package com.test.weather.com.test.weather.presentation.forecast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.weather.R.layout.fragment_forecast
import com.test.weather.com.test.weather.presentation.forecast.recyclerview.ForecastRecyclerViewDelegate
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
        ) {
            parentFragmentManager
                .beginTransaction()
                .addToBackStack("newFragment")
                .commit()
        }
        launchWhenResumed {
            viewModel.viewState.collect {
                viewBinding.tvCurrentTemperature.text = it.currentTemperature
                rvDelegate.setItems(it.forecastList)
            }
        }

    }

    companion object {
        operator fun invoke() = ForecastFragment()
    }
}
