package com.davidluna.architectcoders2024.app.data.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteCast
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteImage
import com.davidluna.architectcoders2024.app.data.remote.services.movies.MovieDetailService
import com.davidluna.architectcoders2024.app.utils.toAppError
import com.davidluna.architectcoders2024.data.sources.MovieDetailsDataSource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.Cast
import com.davidluna.architectcoders2024.domain.responses.movies.Image
import com.davidluna.architectcoders2024.domain.responses.movies.MovieDetail
import com.davidluna.architectcoders2024.domain.responses.movies.YoutubeVideo
import javax.inject.Inject

class ApiMovieDetailsDataSource @Inject constructor(
    private val service: MovieDetailService
) :
    MovieDetailsDataSource {

    override suspend fun getMovieDetail(movieId: Int): Either<AppError, MovieDetail> =
        service.getMovieById(movieId).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun getMovieCast(movieId: Int): Either<AppError, List<Cast>> =
        service.getMovieCredits(movieId).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.cast.map(RemoteCast::toDomain).right() }
        )

    override suspend fun getMovieImages(movieId: Int): Either<AppError, List<Image>> =
        service.getMovieImages(movieId).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.posters.map(RemoteImage::toDomain).right() }
        )


    override suspend fun getMovieVideos(movieId: Int): Either<AppError, List<YoutubeVideo>> =
        service.getMovieVideos(movieId).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = {
                it.results.filter { video -> video.site.uppercase() == SITE.uppercase() }
                    .map { video -> video.toDomain() }.right()
            }
        )

    companion object {
        private const val SITE = "YouTube"
    }
}



