package com.davidluna.tmdb.media_ui.presenter.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.davidluna.tmdb.core_domain.entities.PAGE_SIZE
import com.davidluna.tmdb.core_domain.entities.PREFETCH_DISTANCE
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.media_domain.usecases.GetMediaCatalogUseCase
import kotlinx.coroutines.flow.Flow

context(ViewModel)
fun GetMediaCatalogUseCase.asPagingFlow(
    endpoint: String,
): Flow<PagingData<Media>> = Pager(
    config = PagingConfig(
        pageSize = PAGE_SIZE,
        prefetchDistance = PREFETCH_DISTANCE,
        initialLoadSize = PAGE_SIZE * 2
    ),
    pagingSourceFactory = { MediaPagingSource { this(endpoint, it) } }
).flow.cachedIn(viewModelScope)