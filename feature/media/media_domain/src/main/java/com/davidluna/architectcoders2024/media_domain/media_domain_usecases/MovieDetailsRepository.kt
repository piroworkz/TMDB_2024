package com.davidluna.architectcoders2024.media_domain.media_domain_usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Cast
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.MediaDetails
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Image

interface MovieDetailsRepository {
    suspend fun getMovieDetail(endpoint: String): Either<AppError, MediaDetails>

    suspend fun getMovieCast(endpoint: String): Either<AppError, List<Cast>>

    suspend fun getMovieImages(endpoint: String): Either<AppError, List<Image>>

}