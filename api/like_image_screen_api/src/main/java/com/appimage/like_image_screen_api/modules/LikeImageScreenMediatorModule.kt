package com.appimage.like_image_screen_api.modules

import com.appimage.core.di.mediators.MediatorsMap
import com.appimage.core.di.mediators.getMediator
import com.appimage.core.di.qualifiers.MediatorsQualifier
import com.appimage.like_image_screen_api.mediators.LikeImageScreenMediator
import dagger.Module
import dagger.Provides

@Module
object LikeImageScreenMediatorModule {

    @Provides
    fun provideMainScreenMediator(
        @MediatorsQualifier mediatorsMap: MediatorsMap
    ): LikeImageScreenMediator {
        return mediatorsMap.getMediator()
    }
}