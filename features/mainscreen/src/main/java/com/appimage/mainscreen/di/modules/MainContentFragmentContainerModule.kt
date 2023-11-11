package com.appimage.mainscreen.di.modules

import com.appimage.core.di.qualifiers.MainContentFragmentContainer
import com.appimage.mainscreen.R
import dagger.Module
import dagger.Provides

@Module
object MainContentFragmentContainerModule {

    @Provides
    @MainContentFragmentContainer
    fun provideMainActivityContainer(): Int {
        return R.id.fragment_container
    }
}