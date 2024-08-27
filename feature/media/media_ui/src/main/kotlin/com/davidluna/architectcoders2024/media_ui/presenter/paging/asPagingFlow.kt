package com.davidluna.architectcoders2024.media_ui.presenter.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.davidluna.architectcoders2024.core_domain.entities.PAGE_SIZE
import com.davidluna.architectcoders2024.core_domain.entities.PREFETCH_DISTANCE
import com.davidluna.architectcoders2024.media_domain.entities.Media
import com.davidluna.architectcoders2024.media_domain.usecases.GetMediaCatalogUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

fun GetMediaCatalogUseCase.asPagingFlow(
    endpoint: String,
    scope: CoroutineScope,
): Flow<PagingData<Media>> = Pager(
    config = PagingConfig(
        pageSize = PAGE_SIZE,
        prefetchDistance = PREFETCH_DISTANCE,
        initialLoadSize = PAGE_SIZE * 2
    ),
    pagingSourceFactory = { MediaPagingSource { this(endpoint, it) } }
).flow.cachedIn(scope)
