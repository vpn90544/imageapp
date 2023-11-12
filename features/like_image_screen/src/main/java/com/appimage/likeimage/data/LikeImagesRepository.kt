package com.appimage.likeimage.data

import com.appimage.core.di.db.appDb.AppDataBase
import com.appimage.core.di.db.entity.EntityLikeImagesDB
import com.appimage.core.di.qualifiers.DefaultDbQualifier
import javax.inject.Inject

class LikeImagesRepository @Inject constructor(
    @DefaultDbQualifier
    private val db : AppDataBase
) {
    suspend fun getAllLikeImages(): List<EntityLikeImagesDB>{
        return db.getDao().getLikeImages()
    }

    suspend fun delLikeImage(likeImage: EntityLikeImagesDB) {
        db.getDao().delItemFromLikeImages(id = likeImage.id!!)
    }
}