package com.davidluna.architectcoders2024.videos_data_framework.remote.datasource

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.toAppError
import com.davidluna.architectcoders2024.videos_data_framework.remote.service.VideosService
import com.davidluna.architectcoders2024.videos_data_repositories.VideosDataSource
import com.davidluna.architectcoders2024.videos_domain.videos_domain_entities.YoutubeVideo
import javax.inject.Inject

class ApiVideosDatasource @Inject constructor(
    private val service: VideosService
) : VideosDataSource {

    override suspend fun getVideos(endpoint: String): Either<AppError, List<YoutubeVideo>> =
        service.getVideos(endpoint)
            .fold(
                ifLeft = { it.toAppError().left() },
                ifRight = { it.results.map { video -> video.toDomain() }.right() }
            )
}
