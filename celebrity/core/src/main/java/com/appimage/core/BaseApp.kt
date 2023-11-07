package com.appimage.core

import com.appimage.core.di.providers.ApplicationProvider

interface BaseApp {

    fun getApplicationProvider(): ApplicationProvider
}