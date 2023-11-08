package com.appimage.entry_point.presentation.viewmodel

import androidx.fragment.app.Fragment
import com.appimage.arch.viewmodel.BaseViewModel
import com.appimage.mainscreen_api.mediators.MainScreenMediator
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val mainScreenMediator: MainScreenMediator
)
    : BaseViewModel<com.appimage.entry_point.presentation.state.MainActState>(initialState = com.appimage.entry_point.presentation.state.MainActState()) {

        fun getNExtNAv(action: (Fragment) -> Unit){
            mainScreenMediator.showMainScreenFragment(action)
        }
    }