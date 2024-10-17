package com.davidluna.tmdb.media_data.framework.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.tmdb.core_data.framework.remote.client.get
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteCast
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteCredits
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteImage
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteImages
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteMediaDetail
import com.davidluna.tmdb.media_data.framework.remote.model.toDomain
import com.davidluna.tmdb.media_domain.entities.Cast
import com.davidluna.tmdb.media_domain.entities.Image
import com.davidluna.tmdb.media_domain.entities.MediaDetails
import io.ktor.client.HttpClient

class MediaDetailsService(private val client: HttpClient) : MediaDetailsRemoteDatasource {

    override suspend fun getMovieDetail(endpoint: String): Either<AppError, MediaDetails> =
        client.get<RemoteMediaDetail>(endpoint).fold(
            ifLeft = { it.left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun getMovieCast(endpoint: String): Either<AppError, List<Cast>> =
        client.get<RemoteCredits>("$endpoint/credits").fold(
            ifLeft = { it.left() },
            ifRight = { it.cast.map(RemoteCast::toDomain).right() }
        )

    override suspend fun getMovieImages(endpoint: String): Either<AppError, List<Image>> =
        client.get<RemoteImages>("$endpoint/images").fold(
            ifLeft = { it.left() },
            ifRight = { it.posters.map(RemoteImage::toDomain).right() }
        )

}