package com.davidluna.architectcoders2024.app.ui.screens.movies.detail

import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.domain.ContentKind

sealed interface MovieDetailEvent {
    data class OnNavigate(val destination: Destination?) : MovieDetailEvent
    data object ResetError : MovieDetailEvent
    data class SetContentKind(val contentKind: ContentKind) : MovieDetailEvent
}