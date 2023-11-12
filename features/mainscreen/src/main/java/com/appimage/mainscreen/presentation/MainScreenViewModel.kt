package com.appimage.mainscreen.presentation

import com.appimage.all_image_screen_api.mediators.AllImageScreenMediator
import com.appimage.arch.viewmodel.BaseViewModel
import com.appimage.core.di.qualifiers.ContentFragmentContainer
import com.appimage.core_ui.view.category.CategoryView
import com.appimage.core_ui.view.category.CategoryViewModel
import com.appimage.like_image_screen_api.mediators.LikeImageScreenMediator
import com.appimage.utils.adapter.DelegateItem
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val allImageScreenMediator: AllImageScreenMediator,
    private val likeImageScreenMediator: LikeImageScreenMediator,
    @ContentFragmentContainer
    private val contentContainerId: Int
) : BaseViewModel<MainUiState>(initialState = MainUiState()) {

    override fun bootstrap() {
        super.bootstrap()
        navigateToAllImages()
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
                if (elem.nameCategory == category.nameCategory) {
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
            "All Images" -> { navigateToAllImages() }
            "Like Images" -> { navigateToLikeImages()}
        }
    }

    internal fun updateCountInLikeImages(count:Int) {
        val oldlist = mutableUiState.value.listCategory
        val newList = ArrayList<DelegateItem>()
        for (item in oldlist) {
            if (item is CategoryViewModel) {
                if (item.nameCategory == "Like Images") {
                    newList.add(item.copy(countInCategory = count))
                } else newList.add(item)
            } else newList.add(item)
        }
    }
}