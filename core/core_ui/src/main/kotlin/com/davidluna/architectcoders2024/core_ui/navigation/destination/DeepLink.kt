package com.davidluna.architectcoders2024.core_ui.navigation.destination

import androidx.navigation.NavDeepLink

sealed interface DeepLink : Destination {
    val deepLinks: List<NavDeepLink>
}