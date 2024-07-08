package com.davidluna.architectcoders2024.auth_ui.presenter

import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.navigation.domain.Destination

sealed interface LoginEvent {
    data object CreateRequestToken : LoginEvent
    data object AskForPermission : LoginEvent
    data class CreateSessionId(val requestToken: String) : LoginEvent
    data object CreateGuestSession : LoginEvent
    data object GetAccount : LoginEvent
    data class IsLoggedIn(val destination: Destination?) : LoginEvent
    data class SetError(val error: AppError?) : LoginEvent
}