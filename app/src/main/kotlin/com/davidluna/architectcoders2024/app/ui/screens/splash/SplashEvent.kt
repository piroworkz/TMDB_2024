package com.davidluna.architectcoders2024.app.ui.screens.splash

sealed interface SplashEvent {
    data object OnGranted : SplashEvent
    data object ResetError : SplashEvent
    data object OnLoggedIn : SplashEvent
    data object OnBioFailed : SplashEvent
}