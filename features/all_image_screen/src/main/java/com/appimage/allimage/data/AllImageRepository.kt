package com.appimage.allimage.data

import com.appimage.allimage.data.api.AllImagesService
import com.appimage.allimage.data.models.ImagesInfoPage
import com.appimage.core.di.db.appDb.AppDataBase
import com.appimage.core.di.db.entity.EntityAllImagesDB
import com.appimage.core.di.db.entity.EntityLikeImagesDB
import com.appimage.core.di.qualifiers.DefaultDbQualifier
import com.appimage.core.di.qualifiers.DefaultNetworkApi
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AllImageRepository @Inject constructor(
    private val service: AllImagesService,
    @DefaultDbQualifier
    private val db :AppDataBase,
    @DefaultNetworkApi
    private val context: CoroutineContext
) {
    suspend fun insertLikeOrUnLikeImages(likeImage: EntityLikeImagesDB) {
        db.getDao().delItemFromLikeImages(id = likeImage.id!!)
        db.getDao().insertLikeImage(likeImage)
    }
    suspend fun insertInAllImages(list: List<EntityAllImagesDB>) {
        db.getDao().insertAllImages(list)
    }
    suspend fun clearDbAllImages() {
        db.getDao().clearAllImages()
    }

    suspend fun getLoadAllImagesFromWeb():List<EntityAllImagesDB> {
        return db.getDao().getAllImages()
    }
    suspend fun getLoadNewImagesFromWeb(
        urlNewPage: String
    ): Result<ImagesInfoPage> {
        return withContext(context) {
            service.getImagesNewPage(urlNewPage)
        }
    }

    suspend fun getLoadDefaultImagesFromWeb(
    ): Result<ImagesInfoPage> {
        return withContext(context){
            service.getImagesDefault()
        }
    }
}