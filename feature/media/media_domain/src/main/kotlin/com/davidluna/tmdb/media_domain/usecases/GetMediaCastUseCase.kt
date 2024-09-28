package com.davidluna.tmdb.media_domain.usecases

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_domain.entities.Cast
import javax.inject.Inject

class GetMediaCastUseCase @Inject constructor(private val repository: MovieDetailsRepository) {
    suspend operator fun invoke(endpoint: String): Either<AppError, List<Cast>> =
        repository.getMovieCast(endpoint)
}