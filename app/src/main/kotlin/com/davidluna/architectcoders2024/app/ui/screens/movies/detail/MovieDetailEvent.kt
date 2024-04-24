package com.davidluna.architectcoders2024.app.ui.screens.movies.detail

import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination

sealed interface MovieDetailEvent {
    data class OnNavigate(val destination: Destination?) : MovieDetailEvent
    data object ResetError : MovieDetailEvent
}