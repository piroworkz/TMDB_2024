package com.davidluna.architectcoders2024.app.ui.screens.movies.master

import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.domain.ContentKind

sealed interface MoviesEvent {
    data class SetContentKind(val contentKind: ContentKind): MoviesEvent
    data class OnMovieClicked(val destination: Destination?) : MoviesEvent
    data object ResetError : MoviesEvent
}