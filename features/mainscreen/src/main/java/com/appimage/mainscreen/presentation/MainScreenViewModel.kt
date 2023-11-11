package com.appimage.mainscreen.presentation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.appimage.all_image_screen_api.mediators.AllImageScreenMediator
import com.appimage.arch.viewmodel.BaseViewModel
import com.appimage.like_image_screen_api.mediators.LikeImageScreenMediator
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val allImageScreenMediator: AllImageScreenMediator,
    private val likeImageScreenMediator: LikeImageScreenMediator
) : BaseViewModel<MainUiState>(initialState = MainUiState()) {

    override fun bootstrap() {
        super.bootstrap()
    }

    fun showLikeScreen(action: (Fragment) -> Unit){
        likeImageScreenMediator.showLikeImageScreenFragment { action }
    }
}