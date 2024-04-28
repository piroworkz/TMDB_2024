package com.davidluna.architectcoders2024.app.utils

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.davidluna.architectcoders2024.app.data.remote.model.RemoteError
import com.davidluna.architectcoders2024.app.data.remote.utils.paging.MoviesPagingSource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Movie
import com.davidluna.architectcoders2024.usecases.movies.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow

fun RemoteError.toAppError(): AppError = AppError.Network(
    code = statusCode,
    description = statusMessage,
    successful = success
)

fun GetMoviesUseCase.asPagingFlow(endpoint: String): Flow<PagingData<Movie>> = Pager(
    config = PagingConfig(pageSize = 20, prefetchDistance = 2),
    pagingSourceFactory = { MoviesPagingSource(useCase = this, endpoint = endpoint) }
).flow

fun String.log(name: String = javaClass.simpleName) = Log.d("<-- $name", this)
