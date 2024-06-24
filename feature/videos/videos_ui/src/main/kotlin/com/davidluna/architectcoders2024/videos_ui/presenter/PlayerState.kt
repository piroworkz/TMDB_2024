package com.davidluna.architectcoders2024.videos_ui.presenter

import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind

data class PlayerState(
    val isLoading: Boolean = false,
    val appError: AppError? = null,
    val videos: List<String> = emptyList(),
    val contentKind: ContentKind = ContentKind.MOVIE
)