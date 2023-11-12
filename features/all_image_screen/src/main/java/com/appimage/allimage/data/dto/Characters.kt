package com.appimage.allimage.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ImagesInfoPage(
    val info: InfoPage? = InfoPage(),
    val results: ArrayList<Image> = arrayListOf()
)
@Entity(tableName = "info_character_page")
data class InfoPage (
    @PrimaryKey
    val id_info: Int? = null,
    var next: String? = null,
    var prev: String? = null
)

@Entity(tableName = "table_character")
data class Image(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val image: String? = null
)