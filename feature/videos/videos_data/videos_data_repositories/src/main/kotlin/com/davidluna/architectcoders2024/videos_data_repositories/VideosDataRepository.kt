package com.davidluna.architectcoders2024.videos_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.videos_domain.videos_domain_usecases.VideosRepository
import com.davidluna.architectcoders2024.videos_domain.videos_domain_entities.YoutubeVideo
import javax.inject.Inject

class VideosDataRepository @Inject constructor(private val remote: VideosDataSource) :
    VideosRepository {

    override suspend fun getVideos(endpoint: String): Either<AppError, List<YoutubeVideo>> =
        remote.getVideos(endpoint)

}