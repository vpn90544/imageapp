package com.appimage.storage.modules.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "allImages")
data class EntityAllImagesDB (
    @PrimaryKey(autoGenerate = true)
    val idImage: Int? = null,
    var Image : Int?  = null,
)

@Entity(tableName = "allImages")
data class EntityLikeImagesDB (
    @PrimaryKey(autoGenerate = true)
    val idImage: Int? = null,
    var Image : Int?  = null,
)