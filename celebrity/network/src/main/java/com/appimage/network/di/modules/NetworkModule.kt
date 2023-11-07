package com.appimage.network.di.modules

import com.appimage.core.di.qualifiers.DefaultNetworkApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "http://172.17.1.37:8080"

@Module
object NetworkModule {

    @Provides
    @Singleton
    @DefaultNetworkApi
    fun provideBaseUrl(): HttpUrl {
        return BASE_URL.toHttpUrl()
    }


    @Provides
    @Singleton
    @DefaultNetworkApi
    fun provideRetrofit(
        baseUrl: HttpUrl,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    @DefaultNetworkApi
    fun provideGsonConverterFactory(
        gson: Gson,
    ): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

//    @Provides
//    @Singleton
//    @DefaultNetworkApi
//    fun provideCoroutineContext(): CoroutineContext {
//        val job = SupervisorJob()
//        return (Dispatchers.IO + job)
//    }
}
