package com.appimage.arch.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.appimage.arch.uistate.BaseUiState
import com.appimage.utils.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel <UiState : BaseUiState>(initialState : UiState )
    : ViewModel() {

    private val mutableUiState = MutableStateFlow(initialState)
    val uiState = mutableUiState.asSharedFlow()

    protected var navigator: Navigator? = null
    protected var isInitialized = false
        private set

    internal fun initialize() {
        if (!isInitialized) {
            isInitialized = true
            bootstrap()
        }
    }

    internal fun setNavigator(fragment: Fragment) {
        navigator = Navigator(fragment)
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