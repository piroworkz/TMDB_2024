package com.davidluna.tmdb.videos_domain.data

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.videos_domain.entities.YoutubeVideo

interface VideosDataSource {
    suspend fun getVideos(endpoint: String): Either<AppError, List<YoutubeVideo>>
}