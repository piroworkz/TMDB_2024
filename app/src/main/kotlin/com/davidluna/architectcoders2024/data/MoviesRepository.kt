package com.davidluna.architectcoders2024.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteResults
import com.davidluna.architectcoders2024.app.data.remote.services.movies.MoviesService
import com.davidluna.architectcoders2024.data.MovieType.NOW_PLAYING
import com.davidluna.architectcoders2024.data.MovieType.POPULAR
import com.davidluna.architectcoders2024.data.MovieType.TOP_RATED
import com.davidluna.architectcoders2024.data.MovieType.UPCOMING
import com.davidluna.architectcoders2024.app.utils.toAppError
import com.davidluna.architectcoders2024.domain.AppError

class MoviesRepository(private val service: MoviesService) {

    suspend fun getNowPlayingMovies(page: Int = 1): Either<AppError, RemoteResults<RemoteMovie>> =
        service.getNowPlayingMovies(page).fold(
            ifLeft = { e -> e.toAppError().left() },
            ifRight = { r -> r.copy(results = r.setType(NOW_PLAYING)).right() }
        )

    suspend fun getPopularMovies(page: Int = 1): Either<AppError, RemoteResults<RemoteMovie>> =
        service.getPopularMovies(page).fold(
            ifLeft = { e -> e.toAppError().left() },
            ifRight = { r -> r.copy(results = r.setType(POPULAR)).right() }
        )

    suspend fun getTopRatedMovies(page: Int = 1): Either<AppError, RemoteResults<RemoteMovie>> =
        service.getTopRatedMovies(page).fold(
            ifLeft = { e -> e.toAppError().left() },
            ifRight = { r -> r.copy(results = r.setType(TOP_RATED)).right() }
        )

    suspend fun getUpcomingMovies(page: Int = 1): Either<AppError, RemoteResults<RemoteMovie>> {
        return service.getUpcomingMovies(page).fold(
            ifLeft = { e -> e.toAppError().left() },
            ifRight = { r -> r.copy(results = r.setType(UPCOMING)).right() }
        )
    }

}

private fun RemoteResults<RemoteMovie>.setType(type: MovieType = POPULAR): List<RemoteMovie> {
    return results.map { it.copy(type = type) }
}


enum class MovieType {
    NOW_PLAYING, POPULAR, TOP_RATED, UPCOMING
}