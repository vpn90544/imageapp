package com.appimage.mainscreen.di

import com.appimage.all_image_screen_api.modules.AllImageScreenModule
import com.appimage.core.di.providers.ApplicationProvider
import com.appimage.core.di.scopes.FeatureScope
import com.appimage.like_image_screen_api.modules.LikeImageScreenModule
import com.appimage.mainscreen.di.modules.MainContentFragmentContainerModule
import com.appimage.mainscreen.di.modules.MainScreenViewModelModule
import com.appimage.mainscreen.presentation.MainScreenFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        ApplicationProvider::class,
    ],
    modules = [
        MainScreenViewModelModule::class,
        AllImageScreenModule::class,
        LikeImageScreenModule::class,
        MainContentFragmentContainerModule::class
    ]
)
interface MainScreenComponent {

    companion object {

        fun init(applicationProvider: ApplicationProvider): MainScreenComponent {
            println("комапнент МАЙН фраг создан")
            return DaggerMainScreenComponent.builder()
                .applicationProvider(applicationProvider)
                .build()
        }
    }

    fun inject(fragment: MainScreenFragment)
}