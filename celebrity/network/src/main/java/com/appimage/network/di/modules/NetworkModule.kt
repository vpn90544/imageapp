package com.appimage.network.di.modules

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

private const val BASE_URL = "http://172.17.1.37:8080"
private const val AUTH_URL = "$BASE_URL/login/token"

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): HttpUrl {
        return BASE_URL.toHttpUrl()
    }


    @Provides
    @Singleton
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
    fun provideGsonConverterFactory(
        gson: Gson,
    ): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        val job = SupervisorJob()
        return (Dispatchers.IO + job)
    }
}
