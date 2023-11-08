package com.appimage.mainscreen.presentation.di

import com.appimage.core.di.scopes.FeatureScope
import com.appimage.mainscreen.presentation.MainScreenFragment
import com.appimage.mainscreen.presentation.MainScreenViewModel
import com.appimage.mainscreen.presentation.di.modules.MainScreenViewModelModule
import dagger.Component

@FeatureScope
@Component(
//    dependencies = [
//        ApplicationProvider::class,
//    ],
    modules = [
        MainScreenViewModelModule::class
//        MainScreenRepositoryModule::class,
//        AccountEditScreenModule::class
    ]
)
interface MainScreenComponent {

    companion object {

        fun init(): MainScreenComponent {
            return DaggerMainScreenComponent.builder()
                .build()
        }
    }

    fun inject(fragment: MainScreenFragment)
}