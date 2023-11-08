package com.appimage.core.di.providers

import androidx.room.RoomDatabase
import com.appimage.core.di.BaseDb
import com.appimage.core.di.qualifiers.DefaultDbQualifier


interface StorageProvider {

    @DefaultDbQualifier
    fun provideDataBase(): BaseDb

//    @EncryptedSharedPreference
//    fun provideBaseSharedPreference(): SharedPreferences
}