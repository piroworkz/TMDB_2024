package com.davidluna.architectcoders2024.navigation.domain.destination

import androidx.navigation.NavDeepLink
import com.davidluna.architectcoders2024.navigation.domain.destination.Destination

sealed interface DeepLink : Destination {
    val deepLinks: List<NavDeepLink>
}