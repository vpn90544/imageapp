package com.appimage.mainscreen.data

import com.appimage.core.di.db.appDb.AppDataBase
import com.appimage.core.di.qualifiers.DefaultDbQualifier
import javax.inject.Inject

class MainScreenRepository @Inject constructor(
    @DefaultDbQualifier
    private val db : AppDataBase
) {
    suspend fun getCountFromLikeImagesDb():Int{
        return db.getDao().getCountLikeImages()
    }
}