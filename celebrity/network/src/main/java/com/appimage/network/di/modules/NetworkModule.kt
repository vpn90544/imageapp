package com.appimage.network.di.modules

import com.appimage.core.di.qualifiers.DefaultNetworkApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

const val BASE_URL = "https://rickandmortyapi.com/api/"

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
    fun providesHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    @DefaultNetworkApi
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    @DefaultNetworkApi
    fun provideRetrofit(
        //@DefaultNetworkApi baseUrl: HttpUrl,
        @DefaultNetworkApi gsonConverterFactory: GsonConverterFactory,
        @DefaultNetworkApi okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
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

    @Provides
    @Singleton
    @DefaultNetworkApi
    fun provideCoroutineContext(): CoroutineContext {
        val job = SupervisorJob()
        return (Dispatchers.IO + job)
    }
}
