package com.appimage.allimage.di.modules

import com.appimage.allimage.data.api.AllImagesService
import com.appimage.core.di.qualifiers.DefaultNetworkApi
import com.appimage.core.di.scopes.FeatureScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AllImageRepositoryModule {

    @Provides
    @FeatureScope
    fun provideServiceLoginService(@DefaultNetworkApi retrofit: Retrofit): AllImagesService {
        return retrofit.create(AllImagesService::class.java)
    }
}