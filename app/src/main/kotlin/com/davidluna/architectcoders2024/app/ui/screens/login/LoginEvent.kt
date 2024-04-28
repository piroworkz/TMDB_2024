package com.davidluna.architectcoders2024.app.ui.screens.login

import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination

sealed interface LoginEvent {
    data object CreateRequestToken : LoginEvent
    data object AskForPermission : LoginEvent
    data class CreateSessionId(val requestToken: String) : LoginEvent
    data object GetAccount : LoginEvent
    data object ResetError : LoginEvent
    data class IsLoggedIn(val destination: Destination?) : LoginEvent
}