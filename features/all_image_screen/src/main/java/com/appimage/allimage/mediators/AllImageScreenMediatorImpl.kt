package com.appimage.allimage.mediators

import androidx.fragment.app.Fragment
import com.appimage.all_image_screen_api.mediators.AllImageScreenMediator
import com.appimage.allimage.presentation.AllImageScreenFragment
import javax.inject.Inject

class AllImageScreenMediatorImpl
@Inject constructor() : AllImageScreenMediator {

    override fun showAllImageScreenFragment(action: (Fragment) -> Unit) {
        action(AllImageScreenFragment.newInstance())
    }
}