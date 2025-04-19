package com.davidluna.tmdb.fakes

import androidx.paging.PagingData
import com.davidluna.tmdb.media_domain.entities.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

val empty = emptyFlow<Flow<PagingData<Media>>>()