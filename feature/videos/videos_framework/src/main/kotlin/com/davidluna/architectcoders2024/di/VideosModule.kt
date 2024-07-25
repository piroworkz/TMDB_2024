package com.davidluna.architectcoders2024.di

import com.davidluna.architectcoders2024.framework.remote.service.VideosService
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