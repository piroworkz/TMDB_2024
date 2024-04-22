package com.davidluna.architectcoders2024.app.ui.navigation.destinations

import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultArgs
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.SafeArgs

sealed class InitGraph(
    override val name: String,
    override val args: List<Pair<SafeArgs, Any?>>,
) : Destination {

    data object Init : InitGraph(
        name = INIT,
        args = emptyList()
    )

    data object Splash : InitGraph(
        name = SPLASH,
        args = listOf(DefaultArgs.HideAppBar to DefaultArgs.HideAppBar.defaultValue)
    )

    companion object {
        private const val INIT = "SPLASH_INIT"
        private const val SPLASH = "SPLASH"
    }
}