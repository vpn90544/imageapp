package com.appimage.likeimage.data.mapper

import com.appimage.core.di.db.entity.EntityLikeImagesDB
import com.appimage.core_ui.view.image_with_like.ImageLikeViewModel

class MapperForLikeImage {
    fun mapToEntityDb(image: ImageLikeViewModel): EntityLikeImagesDB {
        return EntityLikeImagesDB(id = image.id, image = image.imageUrl, isLike = image.isLike)
    }

    fun mapToViewModel(image: EntityLikeImagesDB): ImageLikeViewModel {
        return ImageLikeViewModel(id = image.id!!, imageUrl = image.image!!, isLike = image.isLike)
    }

    fun mapToListViewModels(list: List<EntityLikeImagesDB>): List<ImageLikeViewModel> {
        val mappedList = ArrayList<ImageLikeViewModel>()
        for (item in list) {
            mappedList.add(mapToViewModel(item))
        }
        return mappedList
    }
}