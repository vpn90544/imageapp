package com.appimage.mainscreen.mediators

import androidx.fragment.app.Fragment
import com.appimage.mainscreen.presentation.MainScreenFragment
import com.appimage.mainscreen_api.mediators.MainScreenMediator
import javax.inject.Inject

class MainScreenMediatorImpl
@Inject constructor() : MainScreenMediator {

//    override fun showMainScreenFragment(): Fragment {
//        return MainScreenFragment.newInstance()
//    }
    override fun showMainScreenFragment(action: (Fragment) -> Unit) {
        action(MainScreenFragment.newInstance())
    }
}