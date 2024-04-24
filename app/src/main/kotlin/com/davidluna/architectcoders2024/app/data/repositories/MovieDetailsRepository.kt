package com.davidluna.architectcoders2024.app.data.repositories

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.app.data.remote.model.RemoteError
import com.davidluna.architectcoders2024.app.data.remote.model.RemoteVideo
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteImages
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovieCredits
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovieDetail
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteResults
import com.davidluna.architectcoders2024.app.data.remote.services.movies.MovieDetailService

class MovieDetailsRepository(
    private val service: MovieDetailService
) {

    suspend fun getMovieDetail(movieId: Int): Either<RemoteError, RemoteMovieDetail> =
        service.getMovieDetail(movieId)

    suspend fun getMovieCredits(movieId: Int): Either<RemoteError, RemoteMovieCredits> =
        service.getMovieCredits(movieId)

    suspend fun getMovieImages(movieId: Int): Either<RemoteError, RemoteImages> =
        service.getMovieImages(movieId)

    suspend fun getMovieRecommendations(
        movieId: Int,
        page: Int = 1
    ): Either<RemoteError, RemoteResults<RemoteMovie>> =
        service.getMovieRecommendations(movieId, page)

    suspend fun getSimilarMovies(
        movieId: Int,
        page: Int = 1
    ): Either<RemoteError, RemoteResults<RemoteMovie>> =
        service.getSimilarMovies(movieId, page)


    suspend fun getMovieVideos(movieId: Int): Either<RemoteError, List<RemoteVideo>> =
        service.getMovieVideos(movieId).fold(
            ifLeft = { it.left() },
            ifRight = {
                it.results.map { video ->
                    video.copy(
                        order = when (video.type) {
                            "Trailer" -> 1
                            "Teaser" -> 2
                            else -> 3
                        }
                    )
                }.right()
            }
        )

}