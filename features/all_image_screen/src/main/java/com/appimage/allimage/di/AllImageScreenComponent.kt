package com.appimage.allimage.di

import com.appimage.allimage.di.modules.AllImageScreenViewModelModule
import com.appimage.allimage.presentation.AllImageScreenFragment
import com.appimage.core.di.providers.ApplicationProvider
import com.appimage.core.di.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        ApplicationProvider::class,
    ],
    modules = [
        AllImageScreenViewModelModule::class
    ]
)
interface AllImageScreenComponent {

    companion object {

        fun init(applicationProvider: ApplicationProvider): AllImageScreenComponent {
            println("комапнент МАЙН фраг создан")
            return DaggerAllImageScreenComponent.builder()
                .applicationProvider(applicationProvider)
                .build()
        }
    }

    fun inject(fragment: AllImageScreenFragment)
}