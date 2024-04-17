package com.davidluna.architectcoders2024.app.ui.navigation

import android.content.Intent
import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink

sealed class AuthGraph(
    override val name: String,
    override val args: List<Args<Any>>,
    override val deepLinks: List<NavDeepLink> = emptyList()
) : Destination {

    data object Init : AuthGraph(
        name = "init",
        args = emptyList()
    )

    data object Login : AuthGraph(
        name = "login",
        args = emptyList(),
        deepLinks = listOf(
            navDeepLink {
                uriPattern =
                    "https://com.davidluna.architectcoders2024/{${Args.Authentication.name}}"
                action = Intent.ACTION_VIEW
            }
        )
    )
}