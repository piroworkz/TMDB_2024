package com.davidluna.architectcoders2024.app.ui.navigation.destinations

import android.content.Intent
import androidx.core.net.toUri
import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultArgs
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultArgs.HideAppBar
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.SafeArgs

sealed class AuthGraph(
    override val name: String,
    override val args: List<Pair<SafeArgs, Any?>> = emptyList(),
    override val deepLinks: List<NavDeepLink> = emptyList()
) : DeepLink {

    data object Init : AuthGraph(name = INIT)

    data object Login : AuthGraph(
        name = LOGIN,
        args = listOf(
            HideAppBar to HideAppBar.defaultValue,
            DefaultArgs.Auth to DefaultArgs.Auth.defaultValue
        ),
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "https://tmdb.davidluna.com/{${DefaultArgs.Auth.name}}"
                action = Intent.ACTION_VIEW
            }
        )
    )

    companion object {
        private const val INIT = "AUTH_INIT"
        private const val LOGIN = "LOGIN"
    }
}