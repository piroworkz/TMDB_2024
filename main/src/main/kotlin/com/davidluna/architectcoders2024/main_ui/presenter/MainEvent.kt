package com.davidluna.architectcoders2024.main_ui.presenter

import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError
import com.davidluna.architectcoders2024.core_domain.entities.ContentKind

sealed interface MainEvent {
    data object OnCloseSession : MainEvent
    data class SetContentKind(val mediaType: ContentKind) : MainEvent
    data class SetAppError(val appError: AppError?) : MainEvent
}