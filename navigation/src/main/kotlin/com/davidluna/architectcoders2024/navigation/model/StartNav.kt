package com.davidluna.architectcoders2024.navigation.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface StartNav : Destination {

    @Serializable
    data object Init : StartNav

    @Serializable
    data class Splash(
        val hideAppBar: Boolean = true
    ) : StartNav
}