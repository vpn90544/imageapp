package com.appimage.mainscreen.di

import com.appimage.core.di.providers.ApplicationProvider
import com.appimage.core.di.scopes.FeatureScope
import com.appimage.mainscreen.di.modules.MainScreenViewModelModule
import com.appimage.mainscreen.presentation.MainScreenFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        ApplicationProvider::class,
    ],
    modules = [
        MainScreenViewModelModule::class
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