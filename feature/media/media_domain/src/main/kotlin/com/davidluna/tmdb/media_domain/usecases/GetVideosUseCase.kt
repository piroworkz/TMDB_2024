package com.davidluna.tmdb.media_domain.usecases

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_domain.entities.Video
import com.davidluna.tmdb.media_domain.repositories.VideosPlayerRepository

class GetVideosUseCase (private val repository: VideosPlayerRepository) {
    suspend operator fun invoke(endpoint: String): Either<AppError, List<Video>> =
        repository.getVideos(endpoint)
}