package com.davidluna.media_domain.media_domain_usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.media_domain.media_domain_entities.Cast
import com.davidluna.media_domain.media_domain_entities.Details
import com.davidluna.media_domain.media_domain_entities.Image

interface MovieDetailsRepository {
    suspend fun getMovieDetail(endpoint: String): Either<AppError, Details>

    suspend fun getMovieCast(endpoint: String): Either<AppError, List<Cast>>

    suspend fun getMovieImages(endpoint: String): Either<AppError, List<Image>>

}