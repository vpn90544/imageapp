package com.appimage.allimage.data

import com.appimage.allimage.data.api.AllImagesService
import com.appimage.allimage.data.dto.ImagesInfoPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class AllImageRepository @Inject constructor(private val service: AllImagesService) {

    suspend fun getLoadNewImagesFromWeb(
        urlNewPage: String
    ): Response<ImagesInfoPage> {
        return withContext(Dispatchers.IO){
            service.getImagesNewPage(urlNewPage)
        }
    }

    suspend fun getLoadDefaultImagesFromWeb(
    ): Response<ImagesInfoPage> {
        return withContext(Dispatchers.IO){
            service.getImagesDefault()
        }
    }
}