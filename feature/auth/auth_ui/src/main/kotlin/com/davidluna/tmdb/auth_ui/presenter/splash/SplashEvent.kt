package com.davidluna.tmdb.auth_ui.presenter.splash

import com.davidluna.tmdb.auth_ui.view.splash.biometrics.BioResult
import com.davidluna.tmdb.core_ui.navigation.destination.Destination

sealed interface SplashEvent {
    data class NavigateTo(val destination: Destination?) : SplashEvent
    data class LaunchBioPrompt(val launchBioPrompt: Boolean) : SplashEvent
    data class OnBioPromptResult(val result: BioResult) : SplashEvent
    data object ResetAppError : SplashEvent
    data object OnPermissionsGranted : SplashEvent
}