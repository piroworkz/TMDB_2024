package com.davidluna.architectcoders2024.auth_ui.presenter

import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import com.davidluna.architectcoders2024.navigation.domain.destination.Destination

sealed interface LoginEvent {
    data object OnLoginClicked : LoginEvent
    data class CreateSessionId(val requestToken: String) : LoginEvent
    data object CreateGuestSession : LoginEvent
    data object GetAccount : LoginEvent
    data class IsLoggedIn(val destination: Destination?) : LoginEvent
    data class SetError(val error: AppError?) : LoginEvent
}