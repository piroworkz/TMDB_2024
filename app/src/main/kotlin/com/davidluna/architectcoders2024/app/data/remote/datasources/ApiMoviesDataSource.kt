package com.davidluna.architectcoders2024.app.data.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.app.data.remote.services.items.ContentService
import com.davidluna.architectcoders2024.app.utils.toAppError
import com.davidluna.architectcoders2024.data.sources.movies.MoviesDataSource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Content
import com.davidluna.architectcoders2024.domain.responses.movies.Results
import javax.inject.Inject

class ApiMoviesDataSource @Inject constructor(private val service: ContentService) :
    MoviesDataSource {

    override suspend fun getContent(
        endpoint: String,
        page: Int
    ): Either<AppError, Results<Content>> =
        service.getContent(endpoint, page).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )
}