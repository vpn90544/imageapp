package com.appimage.appimage.app

import android.app.Application
import com.appimage.appimage.di.AppComponent
import com.appimage.core.BaseApp
import com.appimage.core.di.providers.ApplicationProvider


class AppImageApp:Application(), BaseApp {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.init(this)
    }

    override fun getApplicationProvider(): ApplicationProvider {
        return appComponent
    }

}