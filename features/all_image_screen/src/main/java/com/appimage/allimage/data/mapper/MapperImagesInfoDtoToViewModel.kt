package com.appimage.allimage.data.mapper

import com.appimage.allimage.data.models.ImagesInfoPage
import com.appimage.core_ui.view.image_with_like.ImageLikeViewModel
import com.appimage.utils.adapter.DelegateItem

class MapperImagesInfoDtoToViewModel {

    fun mapToImageViewModels(dto: ImagesInfoPage?) : List<DelegateItem>{
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
}