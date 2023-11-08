package com.appimage.mainscreen_api.mediators

import androidx.fragment.app.Fragment

interface MainScreenMediator {

    fun showMainScreenFragment(action: (Fragment) -> Unit)
}