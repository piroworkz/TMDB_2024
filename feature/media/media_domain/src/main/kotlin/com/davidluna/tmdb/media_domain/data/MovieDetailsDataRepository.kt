package com.davidluna.tmdb.media_domain.data

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_domain.entities.Cast
import com.davidluna.tmdb.media_domain.entities.Image
import com.davidluna.tmdb.media_domain.entities.MediaDetails
import com.davidluna.tmdb.media_domain.usecases.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsDataRepository @Inject constructor(private val remote: MovieDetailsDataSource) :
    MovieDetailsRepository {

    override suspend fun getMovieDetail(endpoint: String): Either<AppError, MediaDetails> =
        remote.getMovieDetail(endpoint)

    override suspend fun getMovieCast(endpoint: String): Either<AppError, List<Cast>> =
        remote.getMovieCast(endpoint)

    override suspend fun getMovieImages(endpoint: String): Either<AppError, List<Image>> =
        remote.getMovieImages(endpoint)

}




