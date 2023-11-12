package com.appimage.core.di.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "allImages")
data class EntityAllImagesDB (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var image : String?  = null,
    var isLike: Boolean = false
)

@Entity(tableName = "likeImages")
data class EntityLikeImagesDB (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var image : String?  = null,
    var isLike: Boolean = true
)