package com.appimage.utils

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.addOnBackPressedCallback(sourceFragment: Fragment) {
    onBackPressedDispatcher.addCallback(
        sourceFragment,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                sourceFragment.popBackStack()
            }
        }
    )
}