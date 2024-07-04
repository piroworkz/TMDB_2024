package com.davidluna.architectcoders2024.media_ui.presenter.detail

import com.davidluna.architectcoders2024.navigation.domain.Destination

sealed interface MovieDetailEvent {
    data class OnNavigate(val destination: Destination?) :
        MovieDetailEvent
    data object ResetError : MovieDetailEvent
    data object OnViewReady : MovieDetailEvent
}