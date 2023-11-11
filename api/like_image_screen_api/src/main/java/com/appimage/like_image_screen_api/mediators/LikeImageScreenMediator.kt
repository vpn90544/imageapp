package com.appimage.like_image_screen_api.mediators

import androidx.fragment.app.Fragment

interface LikeImageScreenMediator {

    fun showLikeImageScreenFragment(action: (Fragment) -> Unit)
}