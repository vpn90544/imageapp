package com.appimage.allimage.di.modules

import com.appimage.all_image_screen_api.mediators.AllImageScreenMediator
import com.appimage.allimage.mediators.AllImageScreenMediatorImpl
import com.appimage.core.di.qualifiers.MediatorsQualifier
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface AllImageScreenMediatorModule {

    @[Binds IntoMap]
    @MediatorsQualifier
    @ClassKey(AllImageScreenMediator::class)
    fun bindAllImageScreenMediator(impl: AllImageScreenMediatorImpl): Any
}