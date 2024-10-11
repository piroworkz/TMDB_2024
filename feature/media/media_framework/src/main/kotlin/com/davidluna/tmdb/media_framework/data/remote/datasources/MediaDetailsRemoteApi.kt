package com.davidluna.tmdb.media_framework.data.remote.datasources

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.tryCatch
import com.davidluna.tmdb.media_domain.data.MovieDetailsDataSource
import com.davidluna.tmdb.media_domain.entities.Cast
import com.davidluna.tmdb.media_domain.entities.Image
import com.davidluna.tmdb.media_domain.entities.MediaDetails
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteCast
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteCredits
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteImage
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteImages
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteMediaDetail
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


class MediaDetailsRemoteApi (
    private val client: HttpClient
) : MovieDetailsDataSource {

    override suspend fun getMovieDetail(endpoint: String): Either<AppError, MediaDetails> =
        tryCatch(client.get(endpoint).body< RemoteMediaDetail>()::toDomain)

    override suspend fun getMovieCast(endpoint: String): Either<AppError, List<Cast>> =
        tryCatch { client.get("$endpoint/credits").body<RemoteCredits>().cast.map(RemoteCast::toDomain) }

    override suspend fun getMovieImages(endpoint: String): Either<AppError, List<Image>> =
        tryCatch { client.get("$endpoint/images").body<RemoteImages>().posters.map(RemoteImage::toDomain) }

}

