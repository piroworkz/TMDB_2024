package com.davidluna.architectcoders2024.videos_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.videos_domain.videos_domain_entities.YoutubeVideo

interface VideosDataSource {
    suspend fun getVideos(endpoint: String): Either<AppError, List<YoutubeVideo>>
}