package com.appimage.mainscreen_api.mediators

import androidx.fragment.app.Fragment

interface MainScreenMediator {

    //fun showMainScreenFragment(): Fragment
    fun showMainScreenFragment(action: (Fragment) -> Unit)
}