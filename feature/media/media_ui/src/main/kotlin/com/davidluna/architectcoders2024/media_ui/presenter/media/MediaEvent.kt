package com.davidluna.architectcoders2024.media_ui.presenter.media

import com.davidluna.architectcoders2024.core_domain.entities.ContentKind
import com.davidluna.architectcoders2024.core_ui.navigation.destination.Destination

sealed interface MediaEvent {
    data class OnMovieClicked(val destination: Destination?) :
        MediaEvent

    data object ResetError : MediaEvent
    data class OnUiReady(val contentKind: ContentKind) : MediaEvent
}