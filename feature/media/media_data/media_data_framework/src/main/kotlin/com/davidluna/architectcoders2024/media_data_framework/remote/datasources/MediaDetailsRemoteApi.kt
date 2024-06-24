package com.davidluna.architectcoders2024.media_data_framework.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.toAppError
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteCast
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteImage
import com.davidluna.architectcoders2024.media_data_framework.remote.services.MediaDetailService
import com.davidluna.architectcoders2024.media_data_repositories.MovieDetailsDataSource
import com.davidluna.media_domain.media_domain_entities.Cast
import com.davidluna.media_domain.media_domain_entities.Details
import com.davidluna.media_domain.media_domain_entities.Image
import javax.inject.Inject

class MediaDetailsRemoteApi @Inject constructor(
    private val service: MediaDetailService
) : MovieDetailsDataSource {

    override suspend fun getMovieDetail(endpoint: String): Either<AppError, Details> =
        service.getDetailById(endpoint).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun getMovieCast(endpoint: String): Either<AppError, List<Cast>> =
        service.getCreditsById(endpoint).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.cast.map(RemoteCast::toDomain).right() }
        )

    override suspend fun getMovieImages(endpoint: String): Either<AppError, List<Image>> =
        service.getImagesById(endpoint).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.posters.map(RemoteImage::toDomain).right() }
        )

}

