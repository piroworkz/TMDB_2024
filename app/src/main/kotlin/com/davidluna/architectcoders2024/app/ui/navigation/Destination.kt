package com.davidluna.architectcoders2024.app.ui.navigation

import androidx.navigation.NavDeepLink

sealed interface Destination {
    val name: String
    val args: List<Args<Any>>
    val deepLinks: List<NavDeepLink>
}


