package com.appimage.all_image_screen_api.modules

import com.appimage.all_image_screen_api.mediators.AllImageScreenMediator
import com.appimage.core.di.mediators.MediatorsMap
import com.appimage.core.di.mediators.getMediator
import com.appimage.core.di.qualifiers.MediatorsQualifier
import dagger.Module
import dagger.Provides

@Module
object AllImageScreenMediatorModule {

    @Provides
    fun provideMainScreenMediator(
        @MediatorsQualifier mediatorsMap: MediatorsMap
    ): AllImageScreenMediator {
        return mediatorsMap.getMediator()
    }
}