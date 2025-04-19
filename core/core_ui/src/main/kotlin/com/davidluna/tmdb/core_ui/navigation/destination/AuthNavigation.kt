package com.davidluna.tmdb.core_ui.navigation.destination

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthNavigation : Destination {

    @Serializable
    data object Init : AuthNavigation

    @Serializable
    data class Login(
        @SerialName("request_token")
        val requestToken: String = String(),
        @SerialName("approved")
        val approved: Boolean = false,
        val hideAppBar: Boolean = true
    ) : AuthNavigation

}

