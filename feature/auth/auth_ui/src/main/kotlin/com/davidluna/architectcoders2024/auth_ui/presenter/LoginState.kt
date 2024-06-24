package com.davidluna.architectcoders2024.auth_ui.presenter

import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.navigation.model.Destination

data class LoginState(
    val isLoading: Boolean = false,
    val appError: AppError? = null,
    val token: String? = null,
    val intent: Boolean = false,
    val destination: Destination? = null
)