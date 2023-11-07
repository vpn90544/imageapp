package com.appimage.storage.modules

import android.app.Application
import android.content.Context
import androidx.room.RoomDatabase
import com.appimage.core.di.qualifiers.ApplicationContext
import com.appimage.storage.modules.appDb.AppDataBase
import dagger.Module
import dagger.Provides

@Module
object StorageModule {
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Application): AppDataBase {
        return AppDataBase.getAppDataBase(context)
    }
}