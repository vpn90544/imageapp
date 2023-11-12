package com.appimage.core.di.providers

import com.appimage.core.di.qualifiers.DefaultNetworkApi
import com.google.gson.Gson
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

interface NetworkProvider {

//    @DefaultNetworkApi
//    fun provideBaseUrl(): HttpUrl


    @DefaultNetworkApi
    fun provideRetrofit(): Retrofit


    @DefaultNetworkApi
    fun provideGson(): Gson

    @DefaultNetworkApi
    fun provideGsonConverterFactory(): GsonConverterFactory

    @DefaultNetworkApi
    fun provideCoroutineContext(): CoroutineContext
}