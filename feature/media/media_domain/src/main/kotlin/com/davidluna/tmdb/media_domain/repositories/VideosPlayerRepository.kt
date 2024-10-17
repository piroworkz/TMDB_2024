package com.davidluna.tmdb.media_domain.repositories

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_domain.entities.Video

interface VideosPlayerRepository {
    suspend fun getVideos(endpoint: String): Either<AppError, List<Video>>
}