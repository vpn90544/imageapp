package com.appimage.storage.modules.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table")
data class EntityDB (
    @PrimaryKey(autoGenerate = true)
    val idImage: Int? = null,
    var Image : Int?  = null,
)