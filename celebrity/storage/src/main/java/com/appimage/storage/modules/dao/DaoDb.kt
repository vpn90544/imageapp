package com.appimage.storage.modules.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.appimage.storage.modules.dto.EntityAllImagesDB
import com.appimage.storage.modules.dto.EntityLikeImagesDB

@Dao
interface DaoDb {

    @Insert(entity = EntityLikeImagesDB::class)
    fun insertLikeImages(likeImage: EntityLikeImagesDB)

    @Insert(entity = EntityAllImagesDB::class)
    fun insertAllImages(allImages: List<EntityAllImagesDB>)

    @Query("SELECT * FROM allImages")
    fun getAllImages(): List<EntityAllImagesDB>
    @Query("SELECT * FROM likeImages")
    fun getLikeImages():List<EntityLikeImagesDB>
    @Query("DELETE FROM allImages")
    fun clearAllImages()
    @Query("DELETE FROM likeImages WHERE idImage =:idImage")
    fun delItemFromLikeImages(idImage: Int)
}