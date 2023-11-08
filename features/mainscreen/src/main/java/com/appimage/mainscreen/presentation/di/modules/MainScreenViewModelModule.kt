package com.appimage.mainscreen.presentation.di.modules

import androidx.lifecycle.ViewModel
import com.appimage.arch.di.ViewModelKey
import com.appimage.mainscreen.presentation.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainViewModelModule {

    @[Binds IntoMap]
    @ViewModelKey(MainScreenViewModel::class)
    fun bindMainViewModel(viewModel: MainScreenViewModel): ViewModel
}