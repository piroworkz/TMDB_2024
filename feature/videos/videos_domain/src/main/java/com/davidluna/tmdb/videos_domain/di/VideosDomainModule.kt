package com.davidluna.tmdb.videos_domain.di

import com.davidluna.tmdb.videos_domain.data.VideosDataRepository
import com.davidluna.tmdb.videos_domain.usecases.GetVideosUseCase
import com.davidluna.tmdb.videos_domain.usecases.VideosRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val videosDomainModule = module {
    factoryOf(::VideosDataRepository) bind VideosRepository::class
    factoryOf(::GetVideosUseCase)
}