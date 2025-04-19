package com.davidluna.tmdb.videos_framework.data.remote.datasource

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.toAppError
import com.davidluna.tmdb.videos_domain.data.VideosDataSource
import com.davidluna.tmdb.videos_domain.entities.YoutubeVideo
import com.davidluna.tmdb.videos_framework.data.remote.service.VideosService
import javax.inject.Inject

class VideosRemoteApi @Inject constructor(
    private val service: VideosService
) : VideosDataSource {

    override suspend fun getVideos(endpoint: String): Either<AppError, List<YoutubeVideo>> =
        service.getVideos(endpoint).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.results.map { video -> video.toDomain() }.right() }
        )
}
