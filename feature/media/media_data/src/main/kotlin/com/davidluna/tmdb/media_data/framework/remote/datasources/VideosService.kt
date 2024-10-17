package com.davidluna.tmdb.media_data.framework.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.tmdb.core_data.framework.remote.client.get
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteVideo
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteVideos
import com.davidluna.tmdb.media_domain.entities.Video
import io.ktor.client.HttpClient

class VideosService(private val client: HttpClient) : VideosRemoteDataSource {

    override suspend fun getVideos(endpoint: String): Either<AppError, List<Video>> =
        client.get<RemoteVideos>("$endpoint/videos").fold(
            ifLeft = { it.left() },
            ifRight = { it.results.map(RemoteVideo::toDomain).right() }
        )
}