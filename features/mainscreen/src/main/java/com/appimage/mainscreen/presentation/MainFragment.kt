package com.appimage.mainscreen.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.appimage.arch.fragment.BaseFragment
import com.appimage.mainscreen.databinding.MainscreenlayoutBinding

class MainFragment: BaseFragment< MainUiState, MainViewModel, MainscreenlayoutBinding>() {

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): MainscreenlayoutBinding {
        return MainscreenlayoutBinding.inflate(inflater, container, false)
    }


}