package com.davidluna.architectcoders2024.app.data.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteCast
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteImage
import com.davidluna.architectcoders2024.app.data.remote.services.items.ContentDetailService
import com.davidluna.architectcoders2024.app.utils.toAppError
import com.davidluna.architectcoders2024.data.sources.movies.MovieDetailsDataSource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.Cast
import com.davidluna.architectcoders2024.domain.responses.movies.Details
import com.davidluna.architectcoders2024.domain.responses.movies.Image
import com.davidluna.architectcoders2024.domain.responses.movies.YoutubeVideo
import javax.inject.Inject

class ApiMovieDetailsDataSource @Inject constructor(
    private val service: ContentDetailService
) : MovieDetailsDataSource {

    override suspend fun getMovieDetail(endpoint: String): Either<AppError, Details> =
        service.getDetailById(endpoint).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun getMovieCast(endpoint: String): Either<AppError, List<Cast>> =
        service.getCreditsById(endpoint).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.cast.map(RemoteCast::toDomain).right() }
        )

    override suspend fun getMovieImages(endpoint: String): Either<AppError, List<Image>> =
        service.getImagesById(endpoint).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.posters.map(RemoteImage::toDomain).right() }
        )


    override suspend fun getVideos(endpoint: String): Either<AppError, List<YoutubeVideo>> =
        service.getVideos(endpoint).fold(
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



