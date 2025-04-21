package com.davidluna.tmdb.auth_ui.navigation

import com.davidluna.tmdb.core_ui.navigation.Destination
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthNavigation : Destination {

    @Serializable
    data object Init : AuthNavigation

    @Serializable
    data class Login(
        val hideAppBar: Boolean = true,
    ) : AuthNavigation
}