package com.davidluna.architectcoders2024.media_ui.presenter.media

import com.davidluna.architectcoders2024.navigation.domain.Destination

sealed interface MoviesEvent {
    data class OnMovieClicked(val destination: Destination?) :
        MoviesEvent

    data object ResetError : MoviesEvent
}