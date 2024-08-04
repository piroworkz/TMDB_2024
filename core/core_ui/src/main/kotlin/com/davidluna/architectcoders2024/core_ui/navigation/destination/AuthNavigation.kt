package com.davidluna.architectcoders2024.core_ui.navigation.destination

import android.content.Intent
import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.davidluna.architectcoders2024.core_domain.entities.labels.NavArgument
import com.davidluna.architectcoders2024.core_ui.navigation.args.DefaultArgs
import com.davidluna.architectcoders2024.core_ui.navigation.args.SafeArgs


sealed class AuthNavigation(
    override val name: String,
    override val args: List<Pair<SafeArgs, Any?>> = emptyList(),
    override val deepLinks: List<NavDeepLink> = emptyList()
) : DeepLink {

    data object Init : AuthNavigation(name = INIT)

    data object Login : AuthNavigation(
        name = LOGIN,
        args = listOf(
            DefaultArgs.HideAppBar to DefaultArgs.HideAppBar.defaultValue,
            DefaultArgs.Auth to DefaultArgs.Auth.defaultValue
        ),
        deepLinks = listOf(
            navDeepLink {
                uriPattern = URI
                action = Intent.ACTION_VIEW
            }
        )
    )

    companion object {
        private const val INIT = "AUTH_INIT"
        private const val LOGIN = "LOGIN"
        const val URI = "https://tmdb.davidluna.com/{${NavArgument.APPROVED}}"
    }
}