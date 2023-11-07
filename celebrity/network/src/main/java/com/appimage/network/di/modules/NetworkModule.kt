package com.appimage.network.di.modules

import com.appimage.core.di.qualifiers.DefaultNetworkApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "http://172.17.1.37:8080"

@Module
object NetworkModule {

//    @Provides
//    @Singleton
//    @DefaultNetworkApi
//    fun provideBaseUrl(): HttpUrl {
//        return BASE_URL.toHttpUrl()
//    }


    @Provides
    @Singleton
    @DefaultNetworkApi
    fun provideRetrofit(
        //@DefaultNetworkApi baseUrl: HttpUrl,
        @DefaultNetworkApi gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://172.17.1.37:8080")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    @DefaultNetworkApi
    fun provideGsonConverterFactory(
        @DefaultNetworkApi  gson: Gson
    ): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }
    @Singleton
    @Provides
    @DefaultNetworkApi
    fun provideGson():Gson{
        return GsonBuilder().create()
    }

//    @Provides
//    @Singleton
//    @DefaultNetworkApi
//    fun provideCoroutineContext(): CoroutineContext {
//        val job = SupervisorJob()
//        return (Dispatchers.IO + job)
//    }
}
