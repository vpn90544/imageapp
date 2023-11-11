package com.appimage.core.di.providers

import com.appimage.core.di.qualifiers.MainContentFragmentContainer

interface MainContentFragmentContainerProvider {

    @MainContentFragmentContainer
    fun provideMainContentFragmentContainer(): Int
}