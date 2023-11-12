package com.appimage.mainscreen.presentation

import com.appimage.arch.uistate.BaseUiState
import com.appimage.core_ui.view.category.CategoryView
import com.appimage.core_ui.view.category.CategoryViewModel
import com.appimage.utils.adapter.DelegateItem

data class MainUiState (
    val listCategory :ArrayList<DelegateItem> = arrayListOf(
        CategoryViewModel("All Images", isActive = CategoryView.Style.Activated),
        CategoryViewModel("Like Images")
    )
): BaseUiState