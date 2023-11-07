package com.appimage.core.di.providers

import android.app.Application
import com.appimage.core.di.qualifiers.ApplicationContext

interface AndroidDependenciesProvider {

    @ApplicationContext
    fun provideApplicationContext(): Application
}