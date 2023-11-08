package com.appimage.appimage.di

import android.app.Application
import com.appimage.appimage.app.AppImageApp
import com.appimage.appimage.di.modules.MediatorsModule
import com.appimage.arch.di.ViewModelFactoryModule
import com.appimage.core.di.AndroidDependenciesComponent
import com.appimage.core.di.providers.AndroidDependenciesProvider
import com.appimage.core.di.providers.ApplicationProvider
import com.appimage.network.di.modules.NetworkModule
import com.appimage.storage.modules.StorageModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        AndroidDependenciesProvider::class,
                   ],
    modules = [
        NetworkModule::class,
        MediatorsModule::class,
        StorageModule::class,
        ViewModelFactoryModule::class,
    ]
)
interface AppComponent : ApplicationProvider {

    companion object {

        fun init(app: Application): AppComponent {
            val androidDependenciesComponent = AndroidDependenciesComponent.init(app)
            return DaggerAppComponent.builder()
                .androidDependenciesProvider(androidDependenciesComponent)
                .build()
        }
    }

    fun inject(app: AppImageApp)
}