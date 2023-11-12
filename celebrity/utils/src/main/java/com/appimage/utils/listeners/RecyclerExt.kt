package com.appimage.utils.listeners

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setScrollListenerForPaginationInRecycler(
    refreshProgress: ProgressBar,
    loadingProgress: ProgressBar,
    actionForRefresh:()->Unit,
    actionForLoading: () -> Unit
){
    val listener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE &&
                refreshProgress.visibility== View.GONE && loadingProgress.visibility== View.GONE){
                actionForRefresh.invoke()
            }
            if (!recyclerView.canScrollVertically(1)&&newState == RecyclerView.SCROLL_STATE_IDLE &&
                loadingProgress.visibility== View.GONE && refreshProgress.visibility== View.GONE){
                actionForLoading.invoke()
            }
        }
    }
    this.addOnScrollListener(listener)
}