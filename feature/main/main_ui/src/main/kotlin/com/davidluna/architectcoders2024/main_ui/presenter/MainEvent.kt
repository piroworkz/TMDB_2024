package com.davidluna.architectcoders2024.main_ui.presenter

import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind

sealed interface MainEvent {
    data object OnUiReady : MainEvent
    data object OnCloseSession : MainEvent
    data class SetContentKind(val mediaType: ContentKind) : MainEvent
}