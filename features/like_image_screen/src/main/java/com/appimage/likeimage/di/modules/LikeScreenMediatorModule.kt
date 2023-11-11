package com.appimage.likeimage.di.modules

import com.appimage.core.di.qualifiers.MediatorsQualifier
import com.appimage.like_image_screen_api.mediators.LikeImageScreenMediator
import com.appimage.likeimage.mediators.LikeImageScreenMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface LikeScreenMediatorModule {

    @[Binds IntoMap]
    @MediatorsQualifier
    @ClassKey(LikeImageScreenMediator::class)
    fun bindLikeImageScreenMediator(impl: LikeImageScreenMediatorImpl): Any
}