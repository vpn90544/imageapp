package com.appimage.likeimage.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appimage.arch.fragment.BaseFragment
import com.appimage.core.di.providers.ApplicationProvider
import com.appimage.core_ui.view.category.CategoryDelegateAdapter
import com.appimage.core_ui.view.image_with_like.ImageLikeDelegateAdapter
import com.appimage.likeimage.databinding.LikeImageLayoutBinding
import com.appimage.likeimage.di.LikeImageScreenComponent
import com.appimage.utils.adapter.CompositeAdapter
import com.appimage.utils.adapter.decorator.DecoratorParams
import com.appimage.utils.adapter.decorator.ItemsDecorator

class LikeImageScreenFragment
    : BaseFragment<LikeImageScreenState, LikeImageScreenViewModel, LikeImageLayoutBinding>() {

    private val compositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(ImageLikeDelegateAdapter {
                viewModel.clickLikeOrUnLikeImage(it)
            })
            .build()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("FRAGMENT LIKE IMAGE Создане")
        setSettingRecycler()
    }

    private fun setSettingRecycler() {
        viewBinding.recyclerVIew.layoutManager = GridLayoutManager(
            activity,2, RecyclerView.VERTICAL,false
        )
        viewBinding.recyclerVIew.adapter = compositeAdapter
        viewBinding.recyclerVIew.addItemDecoration(
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

    override fun handleUiState(uiState: LikeImageScreenState) {
        super.handleUiState(uiState)
        compositeAdapter.submitList(
            uiState.list
        )
    }

    companion object {

        const val DEFAULT_VALUE_FOR_ITEM_DECORATOR = 8

        fun newInstance(): LikeImageScreenFragment {
            return LikeImageScreenFragment()
        }
    }

    override fun getViewModelClass(): Class<LikeImageScreenViewModel> {
        return LikeImageScreenViewModel::class.java
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): LikeImageLayoutBinding {
        return LikeImageLayoutBinding.inflate(inflater, container, false)
    }

    override fun injectFeatureComponent(applicationProvider: ApplicationProvider) {
        LikeImageScreenComponent.init(applicationProvider).inject(this)
    }

}