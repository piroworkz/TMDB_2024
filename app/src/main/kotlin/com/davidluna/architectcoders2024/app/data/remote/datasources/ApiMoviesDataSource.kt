package com.davidluna.architectcoders2024.app.data.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.app.data.remote.services.movies.MoviesService
import com.davidluna.architectcoders2024.app.utils.toAppError
import com.davidluna.architectcoders2024.data.sources.MoviesDataSource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Movie
import com.davidluna.architectcoders2024.domain.responses.movies.Results
import javax.inject.Inject

class ApiMoviesDataSource @Inject constructor(private val service: MoviesService) :
    MoviesDataSource {

    override suspend fun getMovies(endpoint: String, page: Int): Either<AppError, Results<Movie>> {
        return service.getMovies(endpoint, page).fold(
            ifLeft = {
                it.toAppError().left()
            },
            ifRight = {
                it.toDomain().right()
            }
        )
    }
}