package com.appimage.entry_point.di.modules

import com.appimage.core.di.qualifiers.MainActivityContainer
import com.appimage.entry_point.R
import dagger.Module
import dagger.Provides

@Module
object MainActivityContainerModule {

    @Provides
    @MainActivityContainer
    fun provideMainActivityContainer(): Int {
        return R.id.main_fragment_container
    }
}