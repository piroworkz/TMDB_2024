package com.davidluna.architectcoders2024.auth_ui.presenter

import com.davidluna.architectcoders2024.navigation.model.Destination

sealed interface LoginEvent {
    data object OnUiReady : LoginEvent
    data object CreateRequestToken : LoginEvent
    data object AskForPermission : LoginEvent
    data class CreateSessionId(val requestToken: String) : LoginEvent
    data object CreateGuestSession : LoginEvent
    data object GetAccount : LoginEvent
    data object ResetError : LoginEvent
    data class IsLoggedIn(val destination: Destination?) :
        LoginEvent
}