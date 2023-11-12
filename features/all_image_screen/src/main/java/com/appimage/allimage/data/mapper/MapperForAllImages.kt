package com.appimage.allimage.data.mapper

import com.appimage.allimage.data.models.ImagesInfoPage
import com.appimage.core.di.db.entity.EntityAllImagesDB
import com.appimage.core_ui.view.image_with_like.ImageLikeViewModel
import com.appimage.utils.adapter.DelegateItem

class MapperForAllImages {

    fun mapToImageViewModels(dto: ImagesInfoPage?) : List<DelegateItem> {
        val listViewModels = ArrayList<DelegateItem>()
        dto?.let {
            val results = dto.results
            results?.let {
                for (result in results) {
                    if (result.image!=null && result.id!=null) {
                        listViewModels.add(ImageLikeViewModel(id = result.id!!,
                            imageUrl = result.image!!))
                    }
                }
            }
        }
        return listViewModels
    }

    fun mapToEntityAllImages(list:List<DelegateItem>): List<EntityAllImagesDB> {
        val listEntityAllImages = ArrayList<EntityAllImagesDB>()
        for (item in list) {
            if (item is ImageLikeViewModel) {
                listEntityAllImages.add(
                    EntityAllImagesDB(
                        id = item.id,
                        image = item.imageUrl,
                        isLike = item.isLike
                    )
                )
            }
        }
        return listEntityAllImages
    }
}