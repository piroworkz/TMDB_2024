package com.davidluna.architectcoders2024.framework.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError
import com.davidluna.architectcoders2024.core_domain.entities.errors.toAppError
import com.davidluna.architectcoders2024.framework.remote.media.RemoteCast
import com.davidluna.architectcoders2024.framework.remote.media.RemoteImage
import com.davidluna.architectcoders2024.framework.remote.services.MediaDetailService
import com.davidluna.architectcoders2024.media_domain.data.MovieDetailsDataSource
import com.davidluna.architectcoders2024.media_domain.entities.Cast
import com.davidluna.architectcoders2024.media_domain.entities.Image
import com.davidluna.architectcoders2024.media_domain.entities.MediaDetails
import javax.inject.Inject

class MediaDetailsRemoteApi @Inject constructor(
    private val service: MediaDetailService
) : MovieDetailsDataSource {

    override suspend fun getMovieDetail(endpoint: String): Either<AppError, MediaDetails> =
        service.getDetailById(endpoint).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun getMovieCast(endpoint: String): Either<AppError, List<Cast>> =
        service.getCreditsById(endpoint).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = {
                it.cast.map(RemoteCast::toDomain).right() }
        )

    override suspend fun getMovieImages(endpoint: String): Either<AppError, List<Image>> =
        service.getImagesById(endpoint).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.posters.map(RemoteImage::toDomain).right() }
        )

}
