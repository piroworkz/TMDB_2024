package com.davidluna.architectcoders2024.videos_domain.data

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError
import com.davidluna.architectcoders2024.videos_domain.entities.YoutubeVideo

interface VideosDataSource {
    suspend fun getVideos(endpoint: String): Either<AppError, List<YoutubeVideo>>
}