package com.davidluna.tmdb.videos_framework.di

import com.davidluna.tmdb.videos_framework.data.remote.service.VideosService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VideosModule {

    @Singleton
    @Provides
    fun provideVideosService(retrofit: Retrofit): VideosService =
        retrofit.create(VideosService::class.java)
}