package com.davidluna.tmdb.videos_framework.data.remote.datasource

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.tryCatch
import com.davidluna.tmdb.videos_domain.data.VideosDataSource
import com.davidluna.tmdb.videos_domain.entities.YoutubeVideo
import com.davidluna.tmdb.videos_framework.data.remote.model.RemoteVideo
import com.davidluna.tmdb.videos_framework.data.remote.model.RemoteVideos
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class VideosRemoteApi (private val client: HttpClient) : VideosDataSource {

    override suspend fun getVideos(endpoint: String): Either<AppError, List<YoutubeVideo>> =
        tryCatch { client.get("$endpoint/videos").body<RemoteVideos>().results.map(RemoteVideo::toDomain) }
}
