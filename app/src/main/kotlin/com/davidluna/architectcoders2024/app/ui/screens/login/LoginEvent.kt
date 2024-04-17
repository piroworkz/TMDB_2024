package com.davidluna.architectcoders2024.app.ui.screens.login

sealed interface LoginEvent {
    data object CreateRequestToken : LoginEvent
    data object AskForPermission : LoginEvent
    data class CreateSessionId(val requestToken: String) : LoginEvent
    data object GetAccount : LoginEvent
}