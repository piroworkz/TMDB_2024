package com.davidluna.architectcoders2024.media_ui.presenter.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.davidluna.media_domain.media_domain_entities.Media
import com.davidluna.media_domain.media_domain_usecases.GetContentUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

fun GetContentUseCase.asPagingFlow(
    endpoint: String,
    scope: CoroutineScope
): Flow<PagingData<Media>> = Pager(
    config = PagingConfig(pageSize = 20, prefetchDistance = 2),
    pagingSourceFactory = { MediaPagingSource { this(endpoint, it) } }
).flow.cachedIn(scope)
