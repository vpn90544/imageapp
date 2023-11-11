package com.appimage.arch.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment
import androidx.lifecycle.ViewModel
import com.appimage.utils.Navigate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel <UiState : com.appimage.arch.uistate.BaseUiState>(initialState : UiState )
    : ViewModel() {

    private val mutableUiState = MutableStateFlow(initialState)
    val uiState = mutableUiState.asSharedFlow()

    private var navigator:Navigate? = null
    protected var isInitialized = false
        private set

    internal fun initialize() {
        if (!isInitialized) {
            isInitialized = true
            bootstrap()
        }
    }

    internal fun setNavigator(fragment: Fragment) {
        navigator = Navigate(fragment)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    open fun bootstrap() {
    }

    protected fun updateState(updateAction: (UiState) -> UiState) {
        mutableUiState.update { uiState ->
            updateAction(uiState)
        }
    }
}