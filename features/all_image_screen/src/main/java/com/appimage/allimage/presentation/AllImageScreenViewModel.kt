package com.appimage.allimage.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.appimage.allimage.data.AllImageRepository
import com.appimage.allimage.data.models.ImagesInfoPage
import com.appimage.allimage.data.mapper.MapperForAllImages
import com.appimage.allimage.data.mapper.MapperForLikeImage
import com.appimage.arch.viewmodel.BaseViewModel
import com.appimage.core_ui.view.image_with_like.ImageLikeViewModel
import com.appimage.utils.adapter.DelegateItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
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
            getDefaultLoadImagesFromWeb()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private suspend fun getDefaultLoadImagesFromWeb(): ImagesInfoPage {
        return suspendCoroutine { continuation ->
            viewModelScope.launch {
                repository.getLoadDefaultImagesFromWeb()
                    .onSuccess { result ->
                    continuation.resume(result)
                    result.info?.next?.let {
                        updateUrlNextPage(it)
                    }
                        endLoadingRefresh()
                        updateState { state->
                            state.copy(isRefresh = false)
                        }
                val mapList = mapperAllImages.mapEntityWebToViewModels(result)
                        changeLoadItemsWebWithLikeDbItemsAndUpdate(mapList)
                        println(repository.getLoadAllImagesFromWeb().size)
                }.onFailure {
                    getLoadAllImagesFromDb()
                    endLoadingRefresh()
                }
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private suspend fun getNewLoadImagesWeb(urlNextPage: String): ImagesInfoPage {
        return suspendCoroutine { continuation ->
            viewModelScope.launch {
                repository.getLoadNewImagesFromWeb(urlNextPage)
                    .onSuccess { result ->
                        continuation.resume(result)
                        result.info?.next?.let {
                            updateUrlNextPage(it)
                        }
                        endLoadingNewPage()
                        val mapList = mapperAllImages.mapEntityWebToViewModels(result)
                        changeLoadItemsWebWithLikeDbItemsAndUpdate(mapList,true)
                        println(repository.getLoadAllImagesFromWeb().size)
                    }.onFailure {
                        endLoadingNewPage()
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
                    if (changeItem.isLike){
                        insertLikeOrUnlikeImage(changeItem)
                    } else delLikeImage(changeItem)
                } else updateImages.add(item)
            } else updateImages.add(item)
        }
        updateState { state->
            state.copy(list = updateImages)
        }
    }

    internal fun insertLikeOrUnlikeImage(likeImage: ImageLikeViewModel){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertLikeImages(mapperLikeImages.mapViewModelToEntityDb(likeImage))
        }
    }

    internal fun delLikeImage(likeImage: ImageLikeViewModel){
        viewModelScope.launch (Dispatchers.IO){
            repository.delLikeImage(mapperLikeImages.mapViewModelToEntityDb(likeImage))
        }
    }

    internal fun changeLoadItemsWebWithLikeDbItemsAndUpdate(
        list:List<DelegateItem>,
        isNewPage:Boolean = false
    ){
        viewModelScope.launch (Dispatchers.IO) {
            val listFromLike = repository.getAllLikeImages()
            val mappedListFromLike =  mapperLikeImages.mapListEntityDbToListViewModels(listFromLike)
            val newListAfterCompare = ArrayList<DelegateItem>()
            val likedIds = ArrayList<Int>()
            for (item in mappedListFromLike) {
                likedIds.add(item.id)
            }
            for (item in list) {
                if (item is ImageLikeViewModel) {
                    if (likedIds.contains(item.id)) {
                        newListAfterCompare.add(item.copy(isLike = true))
                    } else newListAfterCompare.add(item)
                } else  newListAfterCompare.add(item)
            }
            updateStateListAndSaveInDb(newListAfterCompare,isNewPage)
        }
    }

    private suspend fun updateStateListAndSaveInDb(newListAfterCompare:List<DelegateItem>, isNewPage: Boolean){
        if (isNewPage){
            val updateListAfterLoadNewPage = ArrayList<DelegateItem>()
            updateListAfterLoadNewPage.addAll(mutableUiState.value.list)
            updateListAfterLoadNewPage.addAll(newListAfterCompare)
            updateState { state->
                state.copy(list = updateListAfterLoadNewPage)
            }
            repository.clearDbAllImages()
            repository.insertInAllImages(mapperAllImages.mapViewModelsToEntityDb(updateListAfterLoadNewPage))
        } else {
            updateState { state->
                state.copy(list = newListAfterCompare)
            }
            repository.clearDbAllImages()
            repository.insertInAllImages(mapperAllImages.mapViewModelsToEntityDb(newListAfterCompare))
        }

    }

    internal fun getLoadAllImagesFromDb() {
        viewModelScope.launch (Dispatchers.IO){
            val updateList = repository.getAllImages()
            updateState { state->
                state.copy(list = mapperAllImages.mapListEntityDbToListViewModels(updateList))
            }
        }
    }

    internal fun updateUrlNextPage(urlNextPage: String) {
        updateState {state->
            state.copy(nextPageLoad = urlNextPage)
        }
    }

    internal fun loadNextPage() {
        updateState { state ->
            state.copy(
                isLoadingNewPage = true
            )
        }
        if (mutableUiState.value.nextPageLoad == null) {
            viewModelScope.launch (Dispatchers.IO){
                getDefaultLoadImagesFromWeb()
            }
        } else {
            viewModelScope.launch (Dispatchers.IO){
                getNewLoadImagesWeb(mutableUiState.value.nextPageLoad!!)
            }
        }
    }
    internal fun pullToRefresh(){
        updateState { state ->
            state.copy(
                isRefresh = true,
                nextPageLoad = null,
            )
        }
        viewModelScope.launch (Dispatchers.IO){
            getDefaultLoadImagesFromWeb()
        }
    }

    private fun endLoadingRefresh() {
        updateState { state->
            state.copy(isRefresh = false)
        }
    }

    private fun endLoadingNewPage() {
        updateState { state->
            state.copy(isLoadingNewPage = false)
        }
    }

}