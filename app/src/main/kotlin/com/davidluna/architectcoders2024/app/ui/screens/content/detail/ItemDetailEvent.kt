package com.davidluna.architectcoders2024.app.ui.screens.content.detail

import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination

sealed interface ItemDetailEvent {
    data class OnNavigate(val destination: Destination?) : ItemDetailEvent
    data object ResetError : ItemDetailEvent
}