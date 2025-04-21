package com.davidluna.tmdb.media_ui.view.utils

import com.davidluna.tmdb.core_domain.entities.AppError

sealed interface UiState<out T> {
    data class Failure(val appError: AppError) : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data object Loading : UiState<Nothing>
}