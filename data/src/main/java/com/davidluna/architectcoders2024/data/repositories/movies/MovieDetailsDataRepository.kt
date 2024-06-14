package com.davidluna.architectcoders2024.data.repositories.movies

import arrow.core.Either
import com.davidluna.architectcoders2024.data.sources.movies.MovieDetailsDataSource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.Cast
import com.davidluna.architectcoders2024.domain.responses.movies.Image
import com.davidluna.architectcoders2024.domain.responses.movies.Details
import com.davidluna.architectcoders2024.domain.responses.movies.YoutubeVideo
import com.davidluna.architectcoders2024.usecases.repositories.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsDataRepository @Inject constructor(private val remote: MovieDetailsDataSource) :
    MovieDetailsRepository {

    override suspend fun getMovieDetail(endpoint:String): Either<AppError, Details> =
        remote.getMovieDetail(endpoint)

    override suspend fun getMovieCast(endpoint:String): Either<AppError, List<Cast>> =
        remote.getMovieCast(endpoint)

    override suspend fun getMovieImages(endpoint:String): Either<AppError, List<Image>> =
        remote.getMovieImages(endpoint)

    override suspend fun getVideos(endpoint:String): Either<AppError, List<YoutubeVideo>> =
        remote.getVideos(endpoint)
}