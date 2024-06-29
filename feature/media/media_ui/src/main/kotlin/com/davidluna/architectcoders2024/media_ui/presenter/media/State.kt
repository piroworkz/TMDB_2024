package com.davidluna.architectcoders2024.media_ui.presenter.media

import androidx.paging.PagingData
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.navigation.domain.Destination
import com.davidluna.media_domain.media_domain_entities.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class State(
    val isLoading: Boolean = false,
    val appError: AppError? = null,
    val destination: Destination? = null,
    val contentKind: ContentKind = ContentKind.MOVIE,
    val firstList: Flow<PagingData<Media>> = emptyFlow(),
    val secondList: Flow<PagingData<Media>> = emptyFlow(),
    val thirdList: Flow<PagingData<Media>> = emptyFlow(),
    val fourthList: Flow<PagingData<Media>> = emptyFlow()
)