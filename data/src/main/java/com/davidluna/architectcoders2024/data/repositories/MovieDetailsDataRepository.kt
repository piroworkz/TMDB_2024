package com.davidluna.architectcoders2024.data.repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.data.sources.MovieDetailsDataSource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.Cast
import com.davidluna.architectcoders2024.domain.responses.movies.Image
import com.davidluna.architectcoders2024.domain.responses.movies.MovieDetail
import com.davidluna.architectcoders2024.domain.responses.movies.YoutubeVideo
import com.davidluna.architectcoders2024.usecases.repositories.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsDataRepository @Inject constructor(private val remote: MovieDetailsDataSource) :
    MovieDetailsRepository {

    override suspend fun getMovieDetail(movieId: Int): Either<AppError, MovieDetail> =
        remote.getMovieDetail(movieId)

    override suspend fun getMovieCast(movieId: Int): Either<AppError, List<Cast>> =
        remote.getMovieCast(movieId)

    override suspend fun getMovieImages(movieId: Int): Either<AppError, List<Image>> =
        remote.getMovieImages(movieId)

    override suspend fun getMovieVideos(movieId: Int): Either<AppError, List<YoutubeVideo>> =
        remote.getMovieVideos(movieId)
}