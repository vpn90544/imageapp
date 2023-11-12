package com.appimage.allimage.presentation

import androidx.lifecycle.viewModelScope
import com.appimage.allimage.data.AllImageRepository
import com.appimage.allimage.data.dto.ImagesInfoPage
import com.appimage.allimage.data.mapper.MapperImagesInfoDtoToViewModel
import com.appimage.arch.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.CharacterData
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AllImageScreenViewModel @Inject constructor(
    private val repository: AllImageRepository
)
    : BaseViewModel<AllImageScreenState>(initialState = AllImageScreenState()) {

    override fun bootstrap() {
        super.bootstrap()
        viewModelScope.launch (Dispatchers.IO){
            getDefaultLoadImages()
        }
    }

    private suspend fun getDefaultLoadImages(): ImagesInfoPage {
        return suspendCoroutine { continuation ->
            viewModelScope.launch {
                repository.getLoadDefaultImagesFromWeb().onSuccess { result ->
                    continuation.resume(result)
                    val mapList = MapperImagesInfoDtoToViewModel().mapToImageViewModels(result)
                    updateState { state->
                        state.copy(list = mapList)
                    }
                }.onFailure {
                    continuation.resumeWithException(it)
                }
            }
        }
    }
}