package com.davidluna.architectcoders2024.splash_ui.presenter

import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.navigation.model.Destination

data class SplashState(
    val loading: Boolean = false,
    val appError: AppError? = null,
    val destination: Destination? = null,
    val sessionExists: Boolean = false,
    val isGranted: Boolean = false,
)
