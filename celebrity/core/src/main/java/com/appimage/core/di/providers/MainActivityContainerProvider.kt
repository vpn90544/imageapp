package com.appimage.core.di.providers

import com.appimage.core.di.qualifiers.MainActivityContainer


interface MainActivityContainerProvider {

    @MainActivityContainer
    fun provideMainActivityContainer(): Int
}