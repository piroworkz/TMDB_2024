package com.davidluna.tmdb.auth_ui.presenter

import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_ui.navigation.destination.Destination

sealed interface LoginEvent {
    data object LoginButtonClicked : LoginEvent
    data object GuestButtonCLicked : LoginEvent
    data class LaunchBioPrompt(val value: Boolean) : LoginEvent
    data class Navigate(val destination: Destination?) : LoginEvent
    data class SetAppError(val error: AppError?) : LoginEvent
}
