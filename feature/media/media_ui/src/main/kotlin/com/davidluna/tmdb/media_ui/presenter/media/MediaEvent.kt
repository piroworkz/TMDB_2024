package com.davidluna.tmdb.media_ui.presenter.media

import com.davidluna.tmdb.core_ui.navigation.destination.Destination

sealed interface MediaEvent {
    data class OnMovieClicked(val destination: Destination?) : MediaEvent
    data object ResetError : MediaEvent
}