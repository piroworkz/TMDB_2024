package com.davidluna.architectcoders2024.usecases.repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.Cast
import com.davidluna.architectcoders2024.domain.responses.movies.Image
import com.davidluna.architectcoders2024.domain.responses.movies.MovieDetail
import com.davidluna.architectcoders2024.domain.responses.movies.YoutubeVideo

interface MovieDetailsRepository {
    suspend fun getMovieDetail(movieId: Int): Either<AppError, MovieDetail>

    suspend fun getMovieCast(movieId: Int): Either<AppError, List<Cast>>

    suspend fun getMovieImages(movieId: Int): Either<AppError, List<Image>>

    suspend fun getMovieVideos(movieId: Int): Either<AppError, List<YoutubeVideo>>
}