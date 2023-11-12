package com.appimage.likeimage.presentation

import com.appimage.arch.uistate.BaseUiState
import com.appimage.core_ui.view.category.CategoryViewModel
import com.appimage.utils.adapter.DelegateItem

data class LikeImageScreenState (
    val list: List<DelegateItem> = listOf()
): BaseUiState