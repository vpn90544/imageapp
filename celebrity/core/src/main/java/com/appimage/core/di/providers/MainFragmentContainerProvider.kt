package com.appimage.core.di.providers

import com.appimage.core.di.qualifiers.MainFragmentContainer


interface MainFragmentContainerProvider {

    @MainFragmentContainer
    fun provideMainFragmentContainer(): Int
}