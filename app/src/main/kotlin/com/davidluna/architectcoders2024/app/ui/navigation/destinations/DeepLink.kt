package com.davidluna.architectcoders2024.app.ui.navigation.destinations

import androidx.navigation.NavDeepLink

sealed interface DeepLink : Destination {
    val deepLinks: List<NavDeepLink>
}