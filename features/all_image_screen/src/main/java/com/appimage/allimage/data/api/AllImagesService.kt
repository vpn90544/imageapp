package com.appimage.allimage.data.api

import com.appimage.allimage.data.models.ImagesInfoPage
import retrofit2.http.GET
import retrofit2.http.Url

interface AllImagesService
{
    @GET("character/")
    suspend fun getImagesDefault(): Result<ImagesInfoPage>
    @GET
    suspend fun getImagesNewPage(@Url url:String): Result<ImagesInfoPage>
}