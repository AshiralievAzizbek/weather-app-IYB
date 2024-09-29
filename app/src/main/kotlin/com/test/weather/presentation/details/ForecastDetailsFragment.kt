package com.test.weather.com.test.weather.presentation.details

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.test.weather.R.layout.fragment_forecast_details
import com.test.weather.com.test.weather.presentation.details.args.ForecastDetailsArgs
import com.test.weather.com.test.weather.presentation.details.recyclerview.ForecastDetailsRecyclerViewDelegate
import com.test.weather.com.test.weather.utils.launchWhenResumed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.test.weather.databinding.FragmentForecastDetailsBinding as ViewBinding

@AndroidEntryPoint
class ForecastDetailsFragment : Fragment(fragment_forecast_details) {

    private val viewModel by viewModels<ForecastDetailsViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewBinding = ViewBinding.bind(view)
        val rvDelegate = ForecastDetailsRecyclerViewDelegate(
            layoutInflater = layoutInflater,
            view = viewBinding.rvTempByHours,
        )
        launchWhenResumed {
            viewModel.viewState.onEach {
                rvDelegate.setItems(it.hours)
            }.launchIn(lifecycleScope)
        }
        val arguments = requireArguments()
        val args = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments.getParcelable(
                ForecastDetailsArgs::class.java.name,
                ForecastDetailsArgs::class.java
            )
        } else {
            arguments.getParcelable(ForecastDetailsArgs::class.java.name)
        }
        viewModel.loadDetails(args?.date)
    }

    companion object {
        operator fun invoke(args: ForecastDetailsArgs): ForecastDetailsFragment {
            return ForecastDetailsFragment().apply {
                arguments = Bundle().apply { putParcelable(args::class.java.name, args) }
            }
        }
    }
}
