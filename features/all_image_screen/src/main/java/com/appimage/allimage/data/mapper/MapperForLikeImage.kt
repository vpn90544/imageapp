package com.appimage.allimage.data.mapper

import com.appimage.core.di.db.entity.EntityLikeImagesDB
import com.appimage.core_ui.view.image_with_like.ImageLikeViewModel

class MapperForLikeImage {
    fun mapToEntityDb(image: ImageLikeViewModel): EntityLikeImagesDB {
        return EntityLikeImagesDB(id = image.id, image = image.imageUrl, isLike = image.isLike)
    }
}