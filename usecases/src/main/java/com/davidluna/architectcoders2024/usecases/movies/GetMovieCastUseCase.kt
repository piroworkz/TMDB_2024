package com.davidluna.architectcoders2024.usecases.movies

import arrow.core.Either
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.Cast
import com.davidluna.architectcoders2024.usecases.repositories.MovieDetailsRepository
import javax.inject.Inject

class GetMovieCastUseCase @Inject constructor(private val repository: MovieDetailsRepository) {
    suspend operator fun invoke(endpoint: String): Either<AppError, List<Cast>> =
        repository.getMovieCast(endpoint)
}