package com.davidluna.tmdb.videos_domain.data

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.videos_domain.entities.YoutubeVideo
import com.davidluna.tmdb.videos_domain.usecases.VideosRepository

class VideosDataRepository (private val remote: VideosDataSource) :
    VideosRepository {

    override suspend fun getVideos(endpoint: String): Either<AppError, List<YoutubeVideo>> =
        remote.getVideos(endpoint)

}