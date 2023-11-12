package com.appimage.allimage.presentation

import com.appimage.arch.uistate.BaseUiState
import com.appimage.core_ui.view.category.CategoryViewModel
import com.appimage.utils.adapter.DelegateItem

data class AllImageScreenState (
    val list: List<DelegateItem> = listOf(),
    val isRefresh: Boolean = false,
    val isLoadingNewPage: Boolean = false,
    val nextPageLoad:String? = null
): BaseUiState