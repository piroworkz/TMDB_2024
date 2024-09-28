package com.davidluna.tmdb.media_ui.presenter.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.davidluna.tmdb.core_domain.entities.PAGE_SIZE
import com.davidluna.tmdb.core_domain.entities.PREFETCH_DISTANCE
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.media_domain.usecases.GetMediaCatalogUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

fun GetMediaCatalogUseCase.asPagingFlow(
    endpoint: String,
    scope: CoroutineScope,
): Flow<PagingData<Media>> = Pager(
    config = PagingConfig(
        pageSize = com.davidluna.tmdb.core_domain.entities.PAGE_SIZE,
        prefetchDistance = com.davidluna.tmdb.core_domain.entities.PREFETCH_DISTANCE,
        initialLoadSize = com.davidluna.tmdb.core_domain.entities.PAGE_SIZE * 2
    ),
    pagingSourceFactory = { MediaPagingSource { this(endpoint, it) } }
).flow.cachedIn(scope)
