package com.davidluna.architectcoders2024.app.utils

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.davidluna.architectcoders2024.app.data.remote.model.RemoteError
import com.davidluna.architectcoders2024.app.ui.paging.MoviesPagingSource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Content
import com.davidluna.architectcoders2024.usecases.movies.GetContentUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

fun RemoteError.toAppError(): AppError = AppError.Network(
    code = statusCode,
    description = statusMessage,
    successful = success
)

fun GetContentUseCase.asPagingFlow(
    endpoint: String,
    scope: CoroutineScope
): Flow<PagingData<Content>> =
    Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { MoviesPagingSource(useCase = this, endpoint = endpoint) }
    )
        .flow
        .cachedIn(scope)

fun String.log(name: String = javaClass.simpleName) = Log.d("<-- $name", this)
