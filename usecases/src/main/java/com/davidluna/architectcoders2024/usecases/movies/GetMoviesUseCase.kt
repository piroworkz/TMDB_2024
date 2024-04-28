package com.davidluna.architectcoders2024.usecases.movies

import arrow.core.Either
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Movie
import com.davidluna.architectcoders2024.domain.responses.movies.Results
import com.davidluna.architectcoders2024.usecases.repositories.MoviesRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MoviesRepository) {
    suspend operator fun invoke(endpoint: String, page: Int): Either<AppError, Results<Movie>> =
        repository.getMovies(endpoint, page)
}