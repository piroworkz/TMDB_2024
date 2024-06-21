package com.davidluna.architectcoders2024.app.ui.navigation.destinations

import android.content.Intent
import androidx.navigation.navDeepLink
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthNav : Destination {

    @Serializable
    data object Init : AuthNav

    @Serializable
    data class Login(val hideAppBar: Boolean = false) : AuthNav {
        @Serializable
        companion object Link {
            const val NAME : String = "APPROVED"
            val link = navDeepLink {
                uriPattern = "https://tmdb.davidluna.com/{$NAME}"
                action = Intent.ACTION_VIEW
            }

        }
    }

}