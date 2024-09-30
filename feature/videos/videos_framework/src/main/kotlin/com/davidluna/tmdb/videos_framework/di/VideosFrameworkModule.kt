package com.davidluna.tmdb.videos_framework.di

import com.davidluna.tmdb.videos_domain.data.VideosDataRepository
import com.davidluna.tmdb.videos_domain.data.VideosDataSource
import com.davidluna.tmdb.videos_domain.usecases.VideosRepository
import com.davidluna.tmdb.videos_framework.data.remote.datasource.VideosRemoteApi
import com.davidluna.tmdb.videos_framework.data.remote.service.VideosService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import retrofit2.Retrofit

val videosFrameworkModule = org.koin.dsl.module {
    singleOf(::provideVideosService)
    singleOf(::VideosRemoteApi) bind VideosDataSource::class
    singleOf(::VideosDataRepository) bind VideosRepository::class
}

private fun provideVideosService(retrofit: Retrofit): VideosService =
    retrofit.create(VideosService::class.java)