package com.davidluna.architectcoders2024.navigation.domain.destination

import com.davidluna.architectcoders2024.navigation.domain.args.DefaultArgs
import com.davidluna.architectcoders2024.navigation.domain.args.SafeArgs

sealed class StartNavigation(
    override val name: String,
    override val args: List<Pair<SafeArgs, Any?>>,
) : Destination {

    data object Init : StartNavigation(
        name = INIT,
        args = emptyList()
    )

    data object Splash : StartNavigation(
        name = SPLASH,
        args = listOf(DefaultArgs.HideAppBar to DefaultArgs.HideAppBar.defaultValue)
    )

    companion object {
        private const val INIT = "SPLASH_INIT"
        private const val SPLASH = "SPLASH"
    }
}