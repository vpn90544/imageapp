package com.appimage.mainscreen.presentation

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
    fun navigateToLikeImages(id:Int) {
        likeImageScreenMediator.showLikeImageScreenFragment {
            navigator?.parentNavigateAndClearBackStack(id,it)
        }
    }
    fun navigateToAllImages(id:Int) {
        allImageScreenMediator.showAllImageScreenFragment {
            navigator?.parentNavigateAndClearBackStack(id,it)
        }
    }
}