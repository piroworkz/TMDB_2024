package com.davidluna.tmdb.core_ui.navigation.destination

import kotlinx.serialization.Serializable

@Serializable
sealed interface StartNavigation : Destination {

    @Serializable
    data object Init : StartNavigation

    @Serializable
    data class Splash(val hideAppBar: Boolean = true) : StartNavigation
}