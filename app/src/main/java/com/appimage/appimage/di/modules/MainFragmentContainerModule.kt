package com.appimage.appimage.di.modules

import com.appimage.appimage.R
import com.appimage.core.di.qualifiers.MainFragmentContainer
import dagger.Module
import dagger.Provides

@Module
object MainFragmentContainerModule {

    @Provides
    @MainFragmentContainer
    fun provideMainFragmentContainer(): Int {
        return R.id.main_fragment_container
    }
}