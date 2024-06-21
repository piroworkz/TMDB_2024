package com.davidluna.architectcoders2024.app.di

import com.davidluna.architectcoders2024.BuildConfig
import com.davidluna.architectcoders2024.app.data.remote.services.session.SessionService
import com.davidluna.architectcoders2024.app.data.remote.services.items.ContentDetailService
import com.davidluna.architectcoders2024.app.data.remote.services.items.ContentService
import com.davidluna.architectcoders2024.app.data.remote.utils.MoviesInterceptor
import com.davidluna.architectcoders2024.app.data.remote.utils.call_adapter.NetworkCallAdapterFactory
import com.davidluna.architectcoders2024.app.di.annotations.ApiKey
import com.davidluna.architectcoders2024.app.di.annotations.BaseUrl
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

    @Singleton
    @Provides
    @ApiKey
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Singleton
    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideClient(
        interceptor: MoviesInterceptor
    ): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
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
    fun provideMoviesService(retrofit: Retrofit): ContentService =
        retrofit.create(ContentService::class.java)

    @Singleton
    @Provides
    fun provideMovieDetailService(retrofit: Retrofit): ContentDetailService =
        retrofit.create(ContentDetailService::class.java)

    @Singleton
    @Provides
    fun provideAuthenticationService(retrofit: Retrofit): SessionService =
        retrofit.create(SessionService::class.java)



}