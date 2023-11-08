package com.appimage.appimage.app

import android.app.Application
import com.appimage.appimage.di.AppComponent
import com.appimage.core.BaseApp
import com.appimage.core.di.providers.ApplicationProvider


class AppImageApp:Application(), BaseApp {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        appComponent = AppComponent.init(this).also { localAppComponent ->
            localAppComponent.inject(this)
            appComponent = localAppComponent
        }
        println("Компонент апп создан")
        super.onCreate()
    }

    override fun getApplicationProvider(): ApplicationProvider {
        return appComponent ?: run {
            AppComponent.init(this).also { localAppComponent ->
                localAppComponent.inject(this)
                appComponent = localAppComponent
            }
        }

    }
}