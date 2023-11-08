package com.appimage.appimage.di

import android.app.Application
import com.appimage.appimage.app.AppImageApp
import com.appimage.appimage.di.modules.MainFragmentContainerModule
import com.appimage.appimage.di.modules.MediatorsModule
import com.appimage.core.di.providers.AndroidDependenciesProvider
import com.appimage.core.di.providers.ApplicationProvider
import com.appimage.core.di.qualifiers.ApplicationContext
import com.appimage.network.di.modules.NetworkModule
import com.appimage.storage.modules.StorageModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        MediatorsModule::class,
        MainFragmentContainerModule::class,
        StorageModule::class,
    ]
)
interface AppComponent : ApplicationProvider {

    companion object {

        fun init(app: Application): AppComponent {
            return DaggerAppComponent.factory()
                .create(app)
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            @ApplicationContext
            applicationContext: Application
        ): AppComponent
    }

    fun inject(app: AppImageApp)
}