package com.appimage.allimage.di.modules

import androidx.lifecycle.ViewModel
import com.appimage.allimage.presentation.AllImageScreenViewModel
import com.appimage.arch.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AllImageScreenViewModelModule {

    @[Binds IntoMap]
    @ViewModelKey(AllImageScreenViewModel::class)
    fun bindAllImageScreenViewModel(viewModel: AllImageScreenViewModel): ViewModel
}