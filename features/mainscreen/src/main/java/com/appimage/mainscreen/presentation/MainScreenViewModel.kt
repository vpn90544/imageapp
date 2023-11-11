package com.appimage.mainscreen.presentation

import android.icu.util.ULocale.Category
import com.appimage.all_image_screen_api.mediators.AllImageScreenMediator
import com.appimage.arch.viewmodel.BaseViewModel
import com.appimage.core.di.qualifiers.MainContentFragmentContainer
import com.appimage.core_ui.view.category.CategoryView
import com.appimage.core_ui.view.category.CategoryViewModel
import com.appimage.like_image_screen_api.mediators.LikeImageScreenMediator
import com.appimage.utils.adapter.DelegateItem
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val allImageScreenMediator: AllImageScreenMediator,
    private val likeImageScreenMediator: LikeImageScreenMediator,
    @MainContentFragmentContainer
    private val contentContainerId: Int
) : BaseViewModel<MainUiState>(initialState = MainUiState()) {

    override fun bootstrap() {
        super.bootstrap()
        navigateToLikeImages()
    }
    fun navigateToLikeImages() {
        likeImageScreenMediator.showLikeImageScreenFragment {
            navigator?.parentNavigateAndClearBackStack(contentContainerId,it)
        }
    }
    fun navigateToAllImages() {
        allImageScreenMediator.showAllImageScreenFragment {
            navigator?.parentNavigateAndClearBackStack(contentContainerId,it)
        }
    }

    fun clickCategory(category: CategoryViewModel) {
        val state = mutableUiState.value.listCategory
        val updateListForState = ArrayList<DelegateItem>()
        for (elem in state) {
            if (elem is CategoryViewModel) {
                if (elem == category) {
                    updateListForState.add(elem.copy(isActive = CategoryView.Style.Activated))
                }
                else {
                    updateListForState.add(elem.copy(isActive = CategoryView.Style.Disabled))
                }
            }
        }
        changeContentScreen(category.nameCategory)
        updateState { state->
            state.copy(listCategory = updateListForState)
        }
    }

    private fun changeContentScreen(categoryName: String) {
        when(categoryName){
            "name1" -> { navigateToLikeImages() }
            "name2" -> { navigateToAllImages() }
        }
    }
}