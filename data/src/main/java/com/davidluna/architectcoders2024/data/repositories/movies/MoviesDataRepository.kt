package com.davidluna.architectcoders2024.data.repositories.movies

import arrow.core.Either
import com.davidluna.architectcoders2024.data.sources.movies.MoviesDataSource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Content
import com.davidluna.architectcoders2024.domain.responses.movies.Results
import com.davidluna.architectcoders2024.usecases.repositories.MoviesRepository
import javax.inject.Inject

class MoviesDataRepository @Inject constructor(
    private val remote: MoviesDataSource
) : MoviesRepository {

    override suspend fun getContent(
        endpoint: String,
        page: Int
    ): Either<AppError, Results<Content>> =
        remote.getContent(endpoint, page)

}