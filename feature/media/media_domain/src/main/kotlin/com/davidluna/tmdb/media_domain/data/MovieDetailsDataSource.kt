package com.davidluna.tmdb.media_domain.data

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_domain.entities.Cast
import com.davidluna.tmdb.media_domain.entities.Image
import com.davidluna.tmdb.media_domain.entities.MediaDetails

interface MovieDetailsDataSource {
    suspend fun getMovieDetail(endpoint: String): Either<AppError, MediaDetails>
    suspend fun getMovieCast(endpoint: String): Either<AppError, List<Cast>>
    suspend fun getMovieImages(endpoint: String): Either<AppError, List<Image>>
}