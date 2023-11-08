package com.appimage.mainscreen_api.modules

import com.appimage.core.di.mediators.MediatorsMap
import com.appimage.core.di.mediators.getMediator
import com.appimage.core.di.qualifiers.MediatorsQualifier
import dagger.Module
import dagger.Provides
import com.appimage.mainscreen_api.mediators.MainScreenMediator

@Module
object MainScreenModule {

    @Provides
    fun provideMainScreenMediator(
        @MediatorsQualifier mediatorsMap: MediatorsMap
    ): MainScreenMediator {
        return mediatorsMap.getMediator()
    }
}