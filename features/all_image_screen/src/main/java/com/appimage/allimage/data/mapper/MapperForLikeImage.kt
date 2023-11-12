package com.appimage.allimage.data.mapper

import com.appimage.core.di.db.entity.EntityLikeImagesDB
import com.appimage.core_ui.view.image_with_like.ImageLikeViewModel

class MapperForLikeImage {
    fun mapViewModelToEntityDb(image: ImageLikeViewModel): EntityLikeImagesDB {
        return EntityLikeImagesDB(id = image.id, image = image.imageUrl, isLike = image.isLike)
    }

    fun mapEntityDbToViewModel(image: EntityLikeImagesDB): ImageLikeViewModel {
        return ImageLikeViewModel(id = image.id!!, imageUrl = image.image!!, isLike = image.isLike)
    }

    fun mapListEntityDbToListViewModels(list: List<EntityLikeImagesDB>): List<ImageLikeViewModel> {
        val mappedList = ArrayList<ImageLikeViewModel>()
        for (item in list) {
            mappedList.add(mapEntityDbToViewModel(item))
        }
        return mappedList
    }
}