package com.davidluna.architectcoders2024.di

import com.davidluna.architectcoders2024.di.qualifiers.ApiKey
import com.davidluna.architectcoders2024.di.qualifiers.BaseUrl
import com.davidluna.architectcoders2024.framework.remote.MoviesInterceptor
import com.davidluna.architectcoders2024.framework.remote.call_adapter.NetworkCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    init {
        System.loadLibrary("native")
    }

    private external fun getApiKey(): String
    private external fun getBaseUrl(): String

    @Singleton
    @Provides
    @ApiKey
    fun provideApiKey(): String = getApiKey()

    @Singleton
    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = getBaseUrl()

    @Singleton
    @Provides
    fun provideClient(
        interceptor: MoviesInterceptor
    ): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.NONE
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(this)
            .build()
    }

    @Singleton
    @Provides
    fun provideJsonConverter(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = true
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        @BaseUrl
        baseUrl: String,
        client: OkHttpClient,
        adapter: NetworkCallAdapterFactory,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(adapter)
            .build()

}