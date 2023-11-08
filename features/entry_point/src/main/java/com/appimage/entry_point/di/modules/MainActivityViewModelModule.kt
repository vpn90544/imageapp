package com.appimage.entry_point.di.modules

import androidx.lifecycle.ViewModel
import com.appimage.arch.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityViewModelModule {

    @[Binds IntoMap]
    @ViewModelKey(com.appimage.entry_point.presentation.viewmodel.MainActivityViewModel::class)
    fun bindMainActViewModel(viewModel: com.appimage.entry_point.presentation.viewmodel.MainActivityViewModel): ViewModel
}