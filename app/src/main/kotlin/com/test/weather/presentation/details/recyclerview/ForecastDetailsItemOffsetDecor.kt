package com.test.weather.com.test.weather.presentation.details.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.weather.R.dimen.forecast_item_horizontal_margin
import ru.surfstudio.android.recycler.decorator.Decorator

object ForecastDetailsItemOffsetDecor : Decorator.OffsetDecor {

    private var horizontalMargin: Int? = null

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        if (horizontalMargin == null) {
            horizontalMargin =
                view.resources.getDimensionPixelOffset(forecast_item_horizontal_margin)
        }
        val currentPosition = recyclerView.findContainingViewHolder(view)?.adapterPosition ?: 0

        val end = if ((recyclerView.adapter?.itemCount?.minus(1)) == currentPosition) {
            horizontalMargin
        } else {
            0
        }
        outRect.set(requireNotNull(horizontalMargin), 0, requireNotNull(end), 0)
    }
}