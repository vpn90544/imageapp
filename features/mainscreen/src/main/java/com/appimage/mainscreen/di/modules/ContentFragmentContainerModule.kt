package com.appimage.mainscreen.di.modules

import com.appimage.core.di.qualifiers.ContentFragmentContainer
import com.appimage.mainscreen.R
import dagger.Module
import dagger.Provides

@Module
object ContentFragmentContainerModule {

    @Provides
    @ContentFragmentContainer
    fun provideContentFragmentContainer(): Int {
        return R.id.fragment_container
    }
}