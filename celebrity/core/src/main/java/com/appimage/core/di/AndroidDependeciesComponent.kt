package com.appimage.core.di

import android.app.Application
import com.appimage.core.di.providers.AndroidDependenciesProvider
import com.appimage.core.di.qualifiers.ApplicationContext
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AndroidDependenciesComponent : AndroidDependenciesProvider {

    companion object {

        fun init(applicationContext: Application): AndroidDependenciesComponent {
            return DaggerAndroidDependenciesComponent.factory()
                .create(
                    applicationContext,
                )
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            @ApplicationContext
            applicationContext: Application,
        ): AndroidDependenciesComponent
    }
}