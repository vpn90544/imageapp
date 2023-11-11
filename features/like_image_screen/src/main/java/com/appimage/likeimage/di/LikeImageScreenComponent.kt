package com.appimage.likeimage.di

import com.appimage.core.di.providers.ApplicationProvider
import com.appimage.core.di.scopes.FeatureScope
import com.appimage.likeimage.di.modules.LikeImageScreenViewModelModule
import com.appimage.likeimage.presentation.LikeImageScreenFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        ApplicationProvider::class,
    ],
    modules = [
        LikeImageScreenViewModelModule::class
    ]
)
interface LikeImageScreenComponent {

    companion object {

        fun init(applicationProvider: ApplicationProvider): LikeImageScreenComponent {
            println("комапнент МАЙН фраг создан")
            return DaggerLikeImageScreenComponent.builder()
                .applicationProvider(applicationProvider)
                .build()
        }
    }

    fun inject(fragment: LikeImageScreenFragment)
}