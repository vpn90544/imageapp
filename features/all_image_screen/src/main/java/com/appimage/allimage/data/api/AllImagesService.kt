package com.appimage.allimage.data.api

import com.appimage.allimage.data.dto.ImagesInfoPage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface AllImagesService
{
    @GET
    suspend fun getImagesDefault(@Url url:String): Result<ImagesInfoPage>
    @GET
    suspend fun getImagesNewPage(@Url url:String): Result<ImagesInfoPage>
}