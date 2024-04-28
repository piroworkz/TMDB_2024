package com.davidluna.architectcoders2024.data.repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.data.sources.MoviesDataSource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Movie
import com.davidluna.architectcoders2024.domain.responses.movies.Results
import com.davidluna.architectcoders2024.usecases.repositories.MoviesRepository
import javax.inject.Inject

class MoviesDataRepository @Inject constructor(
    private val remote: MoviesDataSource
) :
    MoviesRepository {

    override suspend fun getMovies(endpoint: String, page: Int): Either<AppError, Results<Movie>> =
        remote.getMovies(endpoint, page)

}