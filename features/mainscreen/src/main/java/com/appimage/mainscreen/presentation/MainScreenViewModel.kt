package com.appimage.mainscreen.presentation

import com.appimage.all_image_screen_api.mediators.AllImageScreenMediator
import com.appimage.arch.viewmodel.BaseViewModel
import com.appimage.core.di.qualifiers.MainContentFragmentContainer
import com.appimage.like_image_screen_api.mediators.LikeImageScreenMediator
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val allImageScreenMediator: AllImageScreenMediator,
    private val likeImageScreenMediator: LikeImageScreenMediator,
    @MainContentFragmentContainer
    private val contentContainerId: Int
) : BaseViewModel<MainUiState>(initialState = MainUiState()) {

    override fun bootstrap() {
        super.bootstrap()
        navigateToLikeImages(contentContainerId)
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