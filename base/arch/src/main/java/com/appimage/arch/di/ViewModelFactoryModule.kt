package com.appimage.arch.di

import androidx.lifecycle.ViewModelProvider
import com.appimage.arch.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}