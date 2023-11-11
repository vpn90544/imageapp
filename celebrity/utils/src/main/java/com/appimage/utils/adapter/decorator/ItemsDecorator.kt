package com.appimage.utils.adapter.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.appimage.utils.dpToPx

class ItemsDecorator(
    private val params: DecoratorParams
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = params.bottom_dp.dpToPx()
        outRect.top = params.top_dp.dpToPx()
        outRect.left = params.left_dp.dpToPx()
        outRect.right = params.right_dp.dpToPx()
    }
}