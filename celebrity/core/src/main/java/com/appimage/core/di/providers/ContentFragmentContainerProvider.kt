package com.appimage.core.di.providers

import com.appimage.core.di.qualifiers.ContentFragmentContainer

interface ContentFragmentContainerProvider {

    @ContentFragmentContainer
    fun provideContentFragmentContainer(): Int
}