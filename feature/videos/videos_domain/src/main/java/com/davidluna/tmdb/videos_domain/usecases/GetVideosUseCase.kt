package com.davidluna.tmdb.videos_domain.usecases

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.videos_domain.entities.YoutubeVideo

class GetVideosUseCase (private val repository: VideosRepository) {
    suspend operator fun invoke(endpoint: String): Either<AppError, List<YoutubeVideo>> =
        repository.getVideos(endpoint)
}