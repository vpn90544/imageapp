package com.appimage.storage.modules

import android.app.Application
import com.appimage.core.di.BaseDb
import com.appimage.core.di.qualifiers.ApplicationContext
import com.appimage.core.di.qualifiers.DefaultDbQualifier
import com.appimage.core.di.db.appDb.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object StorageModule {
    @Provides
    @Singleton
    @DefaultDbQualifier
    fun provideAppDataBase(@ApplicationContext context: Application): AppDataBase {
        return AppDataBase.getAppDataBase(context)
    }
}