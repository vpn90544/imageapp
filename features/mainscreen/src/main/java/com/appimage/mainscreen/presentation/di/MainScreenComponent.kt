package com.appimage.mainscreen.presentation.di

import com.appimage.core.di.providers.ApplicationProvider
import com.appimage.core.di.scopes.FeatureScope
import com.appimage.mainscreen.presentation.MainScreenFragment
import com.appimage.mainscreen.presentation.MainScreenViewModel
import com.appimage.mainscreen.presentation.di.modules.MainScreenViewModelModule
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        ApplicationProvider::class,
    ],
    modules = [
        MainScreenViewModelModule::class
//        MainScreenRepositoryModule::class,
//        AccountEditScreenModule::class
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