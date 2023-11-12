package com.appimage.allimage.data.mapper

import com.appimage.allimage.data.models.ImagesInfoPage
import com.appimage.core.di.db.entity.EntityAllImagesDB
import com.appimage.core_ui.view.image_with_like.ImageLikeViewModel
import com.appimage.utils.adapter.DelegateItem

class MapperForAllImages {

    fun mapEntityDbToViewModel(image: EntityAllImagesDB): ImageLikeViewModel {
        return ImageLikeViewModel(id = image.id!!, imageUrl = image.image!!, isLike = image.isLike)
    }

    fun mapListEntityDbToListViewModels(list: List<EntityAllImagesDB>): List<ImageLikeViewModel> {
        val mappedList = ArrayList<ImageLikeViewModel>()
        for (item in list) {
            mappedList.add(mapEntityDbToViewModel(item))
        }
        return mappedList
    }

    fun mapEntityWebToViewModels(dto: ImagesInfoPage?) : List<DelegateItem> {
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

    fun mapViewModelsToEntityDb(list:List<DelegateItem>): List<EntityAllImagesDB> {
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