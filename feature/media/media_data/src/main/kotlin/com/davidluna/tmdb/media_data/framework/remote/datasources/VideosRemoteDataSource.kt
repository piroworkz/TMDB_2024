package com.davidluna.tmdb.media_data.framework.remote.datasources

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_domain.entities.Video

interface VideosRemoteDataSource {
    suspend fun getVideos(endpoint: String): Either<AppError, List<Video>>
}