package com.appimage.mainscreen.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.appimage.arch.fragment.BaseFragment
import com.appimage.mainscreen.databinding.MainscreenlayoutBinding

class MainScreenFragment: BaseFragment< MainUiState, MainScreenViewModel, MainscreenlayoutBinding>() {

    override fun getViewModelClass(): Class<MainScreenViewModel> {
        return MainScreenViewModel::class.java
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): MainscreenlayoutBinding {
        return MainscreenlayoutBinding.inflate(inflater, container, false)
    }

    companion object {

        fun newInstance(): MainScreenFragment {
            return MainScreenFragment()
        }
    }

}