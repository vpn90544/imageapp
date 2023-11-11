package com.appimage.likeimage.di.modules

import androidx.lifecycle.ViewModel
import com.appimage.arch.di.ViewModelKey
import com.appimage.likeimage.presentation.LikeImageScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LikeImageScreenViewModelModule {

    @[Binds IntoMap]
    @ViewModelKey(LikeImageScreenViewModel::class)
    fun bindLikeImageScreenViewModel(viewModel: LikeImageScreenViewModel): ViewModel
}