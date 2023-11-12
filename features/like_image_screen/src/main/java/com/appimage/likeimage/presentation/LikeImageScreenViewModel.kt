package com.appimage.likeimage.presentation

import androidx.lifecycle.viewModelScope
import com.appimage.arch.viewmodel.BaseViewModel
import com.appimage.core_ui.view.image_with_like.ImageLikeViewModel
import com.appimage.likeimage.data.LikeImagesRepository
import com.appimage.likeimage.data.mapper.MapperForLikeImage
import com.appimage.utils.adapter.DelegateItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LikeImageScreenViewModel @Inject constructor(
    private val repository: LikeImagesRepository
)
    : BaseViewModel<LikeImageScreenState>(initialState = LikeImageScreenState()) {

    private val mapperLikeImages = MapperForLikeImage()

    override fun bootstrap() {
        super.bootstrap()
        updateImageFromDb()
    }
    internal fun clickLikeOrUnLikeImage(clickItem: ImageLikeViewModel) {
        val images = mutableUiState.value.list
        for (item in images) {
            if (item is ImageLikeViewModel) {
                if (item.id == clickItem.id) {
                    val changeItem = item.copy(isLike = !item.isLike)
                    delLikeImage(changeItem)
                }
            }
        }
    }

    internal fun delLikeImage(likeImage: ImageLikeViewModel){
        viewModelScope.launch (Dispatchers.IO){
            repository.delLikeImage(mapperLikeImages.mapToEntityDb(likeImage))
        }
        updateImageFromDb()
    }

    internal fun updateImageFromDb(){
        viewModelScope.launch (Dispatchers.IO) {
            val updateList = repository.getAllLikeImages()
            val mappedUpdateList = mapperLikeImages.mapToListViewModels(updateList)
            updateState { state->
                state.copy(list = mappedUpdateList)
            }
        }
    }
}