package com.test.weather.com.test.weather.presentation.forecast

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.test.weather.R.layout.fragment_forecast
import com.test.weather.com.test.weather.presentation.forecast.delegate.ForecastRecyclerViewDelegate
import com.test.weather.com.test.weather.utils.launchWhenResumed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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
            // open details
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
