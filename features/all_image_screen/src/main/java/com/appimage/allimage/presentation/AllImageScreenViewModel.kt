package com.appimage.allimage.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.appimage.allimage.data.AllImageRepository
import com.appimage.allimage.data.dto.ImagesInfoPage
import com.appimage.allimage.data.mapper.MapperImagesInfoDtoToViewModel
import com.appimage.arch.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun fromfrgaf(){
        viewModelScope.launch (Dispatchers.IO){
            val rep = getDefaultLoadImages()
            println(rep)
        }
    }

    private suspend fun getDefaultLoadImages(): ImagesInfoPage {
        return suspendCoroutine { continuation ->
            viewModelScope.launch {
                val repo = repository.getLoadDefaultImagesFromWeb()
                    .onSuccess { result ->
                    continuation.resume(result)
                val mapList = MapperImagesInfoDtoToViewModel().mapToImageViewModels(result)
                    //println(result.info)
                    println(result)
                   // withContext(Dispatchers.Main){
//                        updateState { state->
//                            state.copy(list = mapList)
//                        }
//                    }
                }.onFailure {
                    continuation.resumeWithException(it)
                }
            }
        }
    }
}