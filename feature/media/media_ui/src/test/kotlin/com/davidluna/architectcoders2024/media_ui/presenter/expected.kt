package com.davidluna.architectcoders2024.media_ui.presenter

import androidx.paging.PagingData
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

val empty = emptyFlow<Flow<PagingData<Media>>>()