package com.davidluna.tmdb.auth_ui.presenter.login

sealed interface LoginEvent {
    data class LoginButtonClicked(val username: String, val password: String) : LoginEvent
    data class SetPassword(val password: String?) : LoginEvent
    data class SetUsername(val username: String?) : LoginEvent
    data object GuestButtonClicked : LoginEvent
    data object ResetAppError : LoginEvent
}