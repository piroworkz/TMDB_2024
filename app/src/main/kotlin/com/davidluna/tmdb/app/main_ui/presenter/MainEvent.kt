package com.davidluna.tmdb.app.main_ui.presenter

import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.errors.AppError

sealed interface MainEvent {
    data object OnCloseSession : MainEvent
    data class SetContentKind(val mediaType: ContentKind) : MainEvent
    data class SetAppError(val appError: AppError?) : MainEvent
}