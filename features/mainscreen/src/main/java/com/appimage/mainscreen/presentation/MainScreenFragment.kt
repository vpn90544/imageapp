package com.appimage.mainscreen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appimage.arch.fragment.BaseFragment
import com.appimage.core.di.providers.ApplicationProvider
import com.appimage.core_ui.view.category.CategoryDelegateAdapter
import com.appimage.mainscreen.R
import com.appimage.mainscreen.databinding.MainscreenlayoutBinding
import com.appimage.mainscreen.di.MainScreenComponent
import com.appimage.utils.adapter.CompositeAdapter
import com.appimage.utils.adapter.decorator.DecoratorParams
import com.appimage.utils.adapter.decorator.ItemsDecorator
import com.appimage.utils.navigation.Navigator

class MainScreenFragment: BaseFragment< MainUiState, MainScreenViewModel, MainscreenlayoutBinding>() {

    private val compositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(CategoryDelegateAdapter())
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSettingRecycler()
        viewModel.navigateToLikeImageScreen(R.id.fragment_container)
    }

    private fun setSettingRecycler() {
        viewBinding.recyclerView.layoutManager = GridLayoutManager(
            activity,2, RecyclerView.VERTICAL,false
        )
        viewBinding.recyclerView.adapter = compositeAdapter
        viewBinding.recyclerView.addItemDecoration(
            ItemsDecorator(
                DecoratorParams(
                    DEFAULT_VALUE_FOR_ITEM_DECORATOR,
                    DEFAULT_VALUE_FOR_ITEM_DECORATOR,
                    DEFAULT_VALUE_FOR_ITEM_DECORATOR,
                    DEFAULT_VALUE_FOR_ITEM_DECORATOR
                )
            )
        )
    }
    override fun handleUiState(uiState: MainUiState) {
        super.handleUiState(uiState)
        compositeAdapter.submitList(
            listOf(
                uiState.category1,
                uiState.category2
            )
        )
    }

    companion object {

        const val DEFAULT_VALUE_FOR_ITEM_DECORATOR = 8

        fun newInstance(): MainScreenFragment {
            return MainScreenFragment()
        }
    }

    override fun getViewModelClass(): Class<MainScreenViewModel> {
        return MainScreenViewModel::class.java
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): MainscreenlayoutBinding {
        return MainscreenlayoutBinding.inflate(inflater, container, false)
    }

    override fun injectFeatureComponent(applicationProvider: ApplicationProvider) {
        MainScreenComponent.init(applicationProvider).inject(this)
    }

}