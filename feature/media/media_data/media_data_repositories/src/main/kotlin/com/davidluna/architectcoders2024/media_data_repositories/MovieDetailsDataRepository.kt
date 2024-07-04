package com.davidluna.architectcoders2024.media_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.media_domain.media_domain_entities.Cast
import com.davidluna.media_domain.media_domain_entities.Details
import com.davidluna.media_domain.media_domain_entities.Image
import com.davidluna.media_domain.media_domain_usecases.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsDataRepository @Inject constructor(private val remote: MovieDetailsDataSource) :
    MovieDetailsRepository {

    override suspend fun getMovieDetail(endpoint: String): Either<AppError, Details> =
        remote.getMovieDetail(endpoint)

    override suspend fun getMovieCast(endpoint: String): Either<AppError, List<Cast>> =
        remote.getMovieCast(endpoint)

    override suspend fun getMovieImages(endpoint: String): Either<AppError, List<Image>> =
        remote.getMovieImages(endpoint)

}

