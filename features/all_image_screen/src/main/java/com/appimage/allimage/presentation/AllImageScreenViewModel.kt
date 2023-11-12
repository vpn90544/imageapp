package com.appimage.allimage.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.appimage.allimage.data.AllImageRepository
import com.appimage.allimage.data.models.ImagesInfoPage
import com.appimage.allimage.data.mapper.MapperForAllImages
import com.appimage.allimage.data.mapper.MapperForLikeImage
import com.appimage.arch.viewmodel.BaseViewModel
import com.appimage.core.di.db.entity.EntityLikeImagesDB
import com.appimage.core_ui.view.image_with_like.ImageLikeViewModel
import com.appimage.utils.adapter.DelegateItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AllImageScreenViewModel @Inject constructor(
    private val repository: AllImageRepository
)
    : BaseViewModel<AllImageScreenState>(initialState = AllImageScreenState()) {

    private val mapperAllImages = MapperForAllImages()
    private val mapperLikeImages = MapperForLikeImage()
    override fun bootstrap() {
        super.bootstrap()
        viewModelScope.launch (Dispatchers.IO){
            getDefaultLoadImages()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private suspend fun getDefaultLoadImages(): ImagesInfoPage {
        return suspendCoroutine { continuation ->
            viewModelScope.launch {
                repository.getLoadDefaultImagesFromWeb()
                    .onSuccess { result ->
                    continuation.resume(result)
                val mapList = mapperAllImages.mapToImageViewModels(result)
                        updateState { state->
                            state.copy(list = mapList)
                        }
                        repository.clearDbAllImages()
                        repository.insertInAllImages(mapperAllImages.mapToEntityAllImages(result))
                        println(repository.getLoadAllImagesFromWeb().size)
                }.onFailure {
                    continuation.resumeWithException(it)
                }
            }
        }
    }

    internal fun clickLikeOrUnLikeImage(clickItem:ImageLikeViewModel) {
        val images = mutableUiState.value.list
        val updateImages = ArrayList<DelegateItem>()
        for (item in images) {
            if (item is ImageLikeViewModel) {
                if (item.id == clickItem.id) {
                    val changeItem = item.copy(isLike = !item.isLike)
                    updateImages.add(changeItem)
                    insertLikeOrUnlikeImage(changeItem)
                } else {
                    updateImages.add(item)
                }
            } else {
                updateImages.add(item)
            }
        }
        updateState { state->
            state.copy(list = updateImages)
        }
    }

    internal fun insertLikeOrUnlikeImage(likeImage: ImageLikeViewModel){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertLikeOrUnLikeImages(mapperLikeImages.mapToEntityDb(likeImage))
        }
    }
}