package com.davidluna.architectcoders2024.di.data.framework.remote

import com.davidluna.architectcoders2024.auth_data_framework.remote.SessionService
import com.davidluna.architectcoders2024.core_data_framework.di.qualifiers.ApiKey
import com.davidluna.architectcoders2024.core_data_framework.di.qualifiers.BaseUrl
import com.davidluna.architectcoders2024.core_data_framework.remote.MoviesInterceptor
import com.davidluna.architectcoders2024.core_data_framework.remote.call_adapter.NetworkCallAdapterFactory
import com.davidluna.architectcoders2024.media_data_framework.remote.services.MediaCatalogService
import com.davidluna.architectcoders2024.media_data_framework.remote.services.MediaDetailService
import com.davidluna.architectcoders2024.videos_data_framework.remote.service.VideosService
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
object NetworkModule {

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

    @Singleton
    @Provides
    fun provideMoviesService(retrofit: Retrofit): MediaCatalogService =
        retrofit.create(MediaCatalogService::class.java)

    @Singleton
    @Provides
    fun provideMovieDetailService(retrofit: Retrofit): MediaDetailService =
        retrofit.create(MediaDetailService::class.java)

    @Singleton
    @Provides
    fun provideAuthenticationService(retrofit: Retrofit): SessionService =
        retrofit.create(SessionService::class.java)


    @Singleton
    @Provides
    fun provideVideosService(retrofit: Retrofit): VideosService =
        retrofit.create(VideosService::class.java)


}