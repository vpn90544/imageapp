package com.appimage.mainscreen.presentation.di.modules

import com.appimage.core.di.qualifiers.MediatorsQualifier
import com.appimage.mainscreen.presentation.mediators.MainScreenMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import com.appimage.mainscreen_api.mediators.MainScreenMediator

@Module
interface MainScreenMediatorModule {

    @[Binds IntoMap]
    @MediatorsQualifier
    @ClassKey(MainScreenMediator::class)
    fun bindMainScreenMediator(impl: MainScreenMediatorImpl): Any
}