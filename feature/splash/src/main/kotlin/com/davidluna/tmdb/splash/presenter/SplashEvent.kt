package com.davidluna.tmdb.splash.presenter

import com.davidluna.tmdb.core_ui.navigation.destination.Destination
import com.davidluna.tmdb.splash.view.biometrics.BioResult

sealed interface SplashEvent {
    data class NavigateTo(val destination: Destination?) : SplashEvent
    data class LaunchBioPrompt(val launchBioPrompt: Boolean) : SplashEvent
    data class OnBioPromptResult(val result: BioResult) : SplashEvent
    data object ResetAppError : SplashEvent
    data object OnPermissionsGranted : SplashEvent
}