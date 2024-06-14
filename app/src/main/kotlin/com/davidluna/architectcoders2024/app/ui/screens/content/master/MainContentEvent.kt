package com.davidluna.architectcoders2024.app.ui.screens.content.master

import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination

sealed interface MainContentEvent {
    data class OnMovieClicked(val destination: Destination?) : MainContentEvent
    data object ResetError : MainContentEvent
}