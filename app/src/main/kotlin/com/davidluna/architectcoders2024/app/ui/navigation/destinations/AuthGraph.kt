package com.davidluna.architectcoders2024.app.ui.navigation.destinations

import android.content.Intent
import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Default
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Default.HideAppBar
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.SafeArgs

sealed class AuthGraph(
    override val name: String,
    override val args: List<Pair<SafeArgs, Any?>>,
    override val deepLinks: List<NavDeepLink> = emptyList()
) : Destination {

    data object Init : AuthGraph(
        name = INIT,
        args = emptyList()
    )

    data object Login : AuthGraph(
        name = LOGIN,
        args = listOf(HideAppBar to HideAppBar.defaultValue),
        deepLinks = listOf(
            navDeepLink {
                uriPattern =
                    "https://com.davidluna.architectcoders2024/{${Default.Auth.name}}"
                action = Intent.ACTION_VIEW
            }
        )
    )

    companion object {
        private const val INIT = "AUTH_INIT"
        private const val LOGIN = "LOGIN"
    }
}