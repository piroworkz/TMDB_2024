package com.davidluna.architectcoders2024.di

import com.davidluna.architectcoders2024.framework.remote.services.MediaCatalogService
import com.davidluna.architectcoders2024.framework.remote.services.MediaDetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MediaModule {

    @Singleton
    @Provides
    fun provideMoviesService(retrofit: Retrofit): MediaCatalogService =
        retrofit.create(MediaCatalogService::class.java)

    @Singleton
    @Provides
    fun provideMovieDetailService(retrofit: Retrofit): MediaDetailService =
        retrofit.create(MediaDetailService::class.java)

}