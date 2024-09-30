package com.davidluna.tmdb.media_domain.di

import com.davidluna.tmdb.media_domain.data.MediaCatalogDataRepository
import com.davidluna.tmdb.media_domain.data.MovieDetailsDataRepository
import com.davidluna.tmdb.media_domain.repositories.MediaRepository
import com.davidluna.tmdb.media_domain.repositories.MovieDetailsRepository
import com.davidluna.tmdb.media_domain.usecases.FormatDateUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaCastUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaCatalogUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaDetailsUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaImagesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mediaDomainModule = module {
    factoryOf(::MediaCatalogDataRepository) bind MediaRepository::class
    factoryOf(::MovieDetailsDataRepository) bind MovieDetailsRepository::class
    factoryOf(::FormatDateUseCase)
    factoryOf(::GetMediaCastUseCase)
    factoryOf(::GetMediaCatalogUseCase)
    factoryOf(::GetMediaDetailsUseCase)
    factoryOf(::GetMediaImagesUseCase)
}