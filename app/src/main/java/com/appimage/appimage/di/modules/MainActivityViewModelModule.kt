package com.appimage.appimage.di.modules

import androidx.lifecycle.ViewModel
import com.appimage.appimage.MainActivityViewModel
import com.appimage.arch.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityViewModelModule {

    @[Binds IntoMap]
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainActViewModel(viewModel: MainActivityViewModel): ViewModel
}