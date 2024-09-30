package com.davidluna.tmdb.media_framework.di

import com.davidluna.tmdb.media_domain.data.MediaCatalogDataRepository
import com.davidluna.tmdb.media_domain.data.MediaCatalogRemoteDatasource
import com.davidluna.tmdb.media_domain.data.MovieDetailsDataRepository
import com.davidluna.tmdb.media_domain.data.MovieDetailsDataSource
import com.davidluna.tmdb.media_domain.repositories.MediaRepository
import com.davidluna.tmdb.media_domain.repositories.MovieDetailsRepository
import com.davidluna.tmdb.media_framework.data.remote.datasources.MediaCatalogRemoteApi
import com.davidluna.tmdb.media_framework.data.remote.datasources.MediaDetailsRemoteApi
import com.davidluna.tmdb.media_framework.data.remote.services.MediaCatalogService
import com.davidluna.tmdb.media_framework.data.remote.services.MediaDetailService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val mediaFrameworkModule = module {
    singleOf(::provideMoviesService)
    singleOf(::provideMovieDetailService)
    singleOf(::MediaDetailsRemoteApi) bind MovieDetailsDataSource::class
    singleOf(::MediaCatalogRemoteApi) bind MediaCatalogRemoteDatasource::class
    singleOf(::MediaCatalogDataRepository) bind MediaRepository::class
    singleOf(::MovieDetailsDataRepository) bind MovieDetailsRepository::class
}


private fun provideMoviesService(retrofit: Retrofit): MediaCatalogService =
    retrofit.create(MediaCatalogService::class.java)

private fun provideMovieDetailService(retrofit: Retrofit): MediaDetailService =
    retrofit.create(MediaDetailService::class.java)
