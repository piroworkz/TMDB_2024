package com.davidluna.tmdb.media_ui.presenter.model

import androidx.paging.PagingData
import com.davidluna.tmdb.media_domain.entities.Media
import kotlinx.coroutines.flow.Flow

data class Catalog(
    val catalogName: String,
    val flow: Flow<PagingData<Media>>
)