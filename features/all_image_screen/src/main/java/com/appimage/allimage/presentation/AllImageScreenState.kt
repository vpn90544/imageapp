package com.appimage.allimage.presentation

import com.appimage.arch.uistate.BaseUiState
import com.appimage.core_ui.view.category.CategoryViewModel
import com.appimage.utils.adapter.DelegateItem

data class AllImageScreenState (
    val category1: DelegateItem = CategoryViewModel("name1"),
    val category2: DelegateItem = CategoryViewModel("name2", countInCategory = 0)
): BaseUiState