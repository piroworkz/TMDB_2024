package com.davidluna.architectcoders2024.fakes

import androidx.paging.PagingData
import com.davidluna.architectcoders2024.media_domain.entities.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

val empty = emptyFlow<Flow<PagingData<Media>>>()