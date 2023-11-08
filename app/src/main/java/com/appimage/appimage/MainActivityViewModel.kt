package com.appimage.appimage

import androidx.fragment.app.Fragment
import com.appimage.arch.viewmodel.BaseViewModel
import com.appimage.mainscreen.presentation.MainUiState
import com.appimage.mainscreen_api.mediators.MainScreenMediator
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val mainScreenMediator: MainScreenMediator
)
    : BaseViewModel<MainActState>(initialState = MainActState()) {

        fun getNExtNAv(){
            mainScreenMediator.showMainScreenFragment({})
        }
    }