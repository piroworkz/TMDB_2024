package com.davidluna.tmdb.media_data.repositories

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_data.framework.remote.datasources.VideosRemoteDataSource
import com.davidluna.tmdb.media_domain.entities.Video
import com.davidluna.tmdb.media_domain.repositories.VideosPlayerRepository

class VideoPlayerDataRepository (private val remote: VideosRemoteDataSource) :
    VideosPlayerRepository {

    override suspend fun getVideos(endpoint: String): Either<AppError, List<Video>> =
        remote.getVideos(endpoint)

}