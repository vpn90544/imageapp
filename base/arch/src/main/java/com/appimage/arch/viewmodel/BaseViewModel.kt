package com.appimage.arch.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel <UiState : com.appimage.arch.uistate.BaseUiState>(initialState : UiState )
    : ViewModel() {

    private val mutableUiState = MutableStateFlow(initialState)
    val uiState = mutableUiState.asSharedFlow()

    protected var isInitialized = false
        private set

    internal fun initialize() {
        if (!isInitialized) {
            isInitialized = true
            bootstrap()
        }
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