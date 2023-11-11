package com.appimage.mainscreen.presentation

import com.appimage.arch.uistate.BaseUiState
import com.appimage.core_ui.view.category.CategoryViewModel
import com.appimage.utils.adapter.DelegateItem

data class MainUiState (
    val category1:DelegateItem = CategoryViewModel("name1"),
    val category2: DelegateItem = CategoryViewModel("name2", countInCategory = 0)
): BaseUiState