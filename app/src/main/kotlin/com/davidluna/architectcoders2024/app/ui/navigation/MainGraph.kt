package com.davidluna.architectcoders2024.app.ui.navigation

import androidx.navigation.NavDeepLink
import com.davidluna.architectcoders2024.app.ui.navigation.Args.IsTopLevelDestination

sealed class MainGraph(
    override val name: String,
    override val args: List<Args<Any>>,
    override val deepLinks: List<NavDeepLink> = emptyList()
) : Destination {

    data object Init : MainGraph(
        name = "main_init",
        args = emptyList()
    )

    data object Home : MainGraph(
        name = MAIN,
        args = listOf(IsTopLevelDestination(true)),
    )

    data object Detail : MainGraph(
        name = DETAIL,
        args = listOf(Args.Detail),
    )

    companion object {
        private const val MAIN = "MAIN"
        private const val DETAIL = "DETAIL"
    }

}