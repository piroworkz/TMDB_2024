package com.davidluna.architectcoders2024.navigation.domain

import android.content.Intent
import androidx.navigation.navDeepLink
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthNav : Destination {

    @Serializable
    data object Init : AuthNav

    @Serializable
    data class Login(val hideAppBar: Boolean = true) : AuthNav {
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