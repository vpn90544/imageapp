package com.appimage.mainscreen.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.appimage.arch.fragment.BaseFragment
import com.appimage.mainscreen.databinding.MainscreenlayoutBinding
import com.appimage.mainscreen.presentation.di.MainScreenComponent

class MainScreenFragment: BaseFragment< MainUiState, MainScreenViewModel, MainscreenlayoutBinding>() {

    override fun getViewModelClass(): Class<MainScreenViewModel> {
        return MainScreenViewModel::class.java
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): MainscreenlayoutBinding {
        return MainscreenlayoutBinding.inflate(inflater, container, false)
    }

    override fun injectFeatureComponent() {
        MainScreenComponent.init().inject(this)
    }

    companion object {

        fun newInstance(): MainScreenFragment {
            return MainScreenFragment()
        }
    }

}