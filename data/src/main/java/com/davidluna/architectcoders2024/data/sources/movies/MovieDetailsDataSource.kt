package com.davidluna.architectcoders2024.data.sources.movies

import arrow.core.Either
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.Cast
import com.davidluna.architectcoders2024.domain.responses.movies.Image
import com.davidluna.architectcoders2024.domain.responses.movies.Details
import com.davidluna.architectcoders2024.domain.responses.movies.YoutubeVideo

interface MovieDetailsDataSource {
    suspend fun getMovieDetail(endpoint: String): Either<AppError, Details>
    suspend fun getMovieCast(endpoint: String): Either<AppError, List<Cast>>
    suspend fun getMovieImages(endpoint: String): Either<AppError, List<Image>>
    suspend fun getVideos(endpoint: String): Either<AppError, List<YoutubeVideo>>
}