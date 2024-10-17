package com.davidluna.tmdb.core_ui.navigation.destination

import android.content.Intent
import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthNavigation : Destination {

    @Serializable
    data object Init : AuthNavigation


    @Serializable
    data class Splash(val hideAppBar: Boolean = true) : AuthNavigation

    @Serializable
    data class Login(
        @SerialName("request_token")
        val requestToken: String = String(),
        @SerialName("approved")
        val approved: Boolean = false,
        val hideAppBar: Boolean = true,
    ) : AuthNavigation

    companion object {
        val deepLink: List<NavDeepLink> = listOf(navDeepLink<Login>(
            basePath = "https://deeplinks-d440b.web.app/login/"
        ) {
            action = Intent.ACTION_VIEW
        })
    }

}

