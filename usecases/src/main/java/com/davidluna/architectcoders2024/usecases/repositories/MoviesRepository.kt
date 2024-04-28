package com.davidluna.architectcoders2024.usecases.repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Movie
import com.davidluna.architectcoders2024.domain.responses.movies.Results

interface MoviesRepository {
    suspend fun getMovies(endpoint: String, page: Int): Either<AppError, Results<Movie>>
}
