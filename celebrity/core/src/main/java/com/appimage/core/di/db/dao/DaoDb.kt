package com.appimage.core.di.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.appimage.core.di.db.entity.EntityAllImagesDB
import com.appimage.core.di.db.entity.EntityLikeImagesDB

@Dao
interface DaoDb {

    @Insert(entity = EntityLikeImagesDB::class)
    fun insertLikeImage(likeImage: EntityLikeImagesDB)

    @Insert(entity = EntityAllImagesDB::class)
    fun insertAllImages(allImages: List<EntityAllImagesDB>)

    @Query("SELECT * FROM allImages")
    fun getAllImages(): List<EntityAllImagesDB>
    @Query("SELECT * FROM likeImages")
    fun getLikeImages():List<EntityLikeImagesDB>
    @Query("DELETE FROM allImages")
    fun clearAllImages()
    @Query("DELETE FROM likeImages WHERE id =:id")
    fun delItemFromLikeImages(id: Int)
}