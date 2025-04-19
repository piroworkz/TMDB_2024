package com.davidluna.tmdb.videos_domain.usecases

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.videos_domain.entities.YoutubeVideo

interface VideosRepository {
    suspend fun getVideos(endpoint: String): Either<AppError, List<YoutubeVideo>>
}