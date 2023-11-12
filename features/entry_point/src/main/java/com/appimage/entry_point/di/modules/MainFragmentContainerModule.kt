package com.appimage.entry_point.di.modules

import com.appimage.core.di.qualifiers.MainFragmentContainer
import com.appimage.entry_point.R
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