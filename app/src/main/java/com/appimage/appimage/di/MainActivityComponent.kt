package com.appimage.appimage.di

import com.appimage.appimage.MainActivity
import com.appimage.appimage.di.modules.MainActivityViewModelModule
import com.appimage.appimage.di.modules.MediatorsModule
import com.appimage.core.di.providers.ApplicationProvider
import com.appimage.mainscreen_api.modules.MainScreenModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        ApplicationProvider::class,
    ],
    modules = [
        MainScreenModule::class,
        MainActivityViewModelModule::class
    ]
)
internal interface MainActivityComponent {

    companion object {

        fun init(applicationProvider: ApplicationProvider): MainActivityComponent {
            return DaggerMainActivityComponent.factory()
                .create(applicationProvider)
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            applicationProvider: ApplicationProvider
        ): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}