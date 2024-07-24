package com.davidluna.architectcoders2024.auth_ui.presenter

import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import com.davidluna.architectcoders2024.navigation.domain.destination.Destination

sealed interface LoginEvent {
    data object LoginButtonClicked : LoginEvent
    data object GuestButtonCLicked : LoginEvent
    data class LaunchBioPrompt(val value: Boolean) : LoginEvent
    data class Navigate(val destination: Destination?) : LoginEvent
    data class SetAppError(val error: AppError?) : LoginEvent
}
