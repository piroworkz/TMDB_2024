package com.davidluna.tmdb.auth_ui.navigation

import com.davidluna.tmdb.core_ui.navigation.Destination
import kotlinx.serialization.Serializable

@Serializable
sealed interface InitialNavigation : Destination {

    @Serializable
    data object Init : InitialNavigation

    @Serializable
    data class Splash(
        val hideAppBar: Boolean = true
    ) : InitialNavigation

}