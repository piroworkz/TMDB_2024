package com.davidluna.architectcoders2024.splash_ui.presenter

sealed interface SplashEvent {
    data object OnGranted : SplashEvent
    data object ResetError : SplashEvent
    data object OnLoggedIn : SplashEvent
    data object OnBioFailed : SplashEvent
}