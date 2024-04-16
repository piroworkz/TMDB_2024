package com.davidluna.architectcoders2024.app.ui.navigation

import com.davidluna.architectcoders2024.app.ui.navigation.Args.IsTopLevelDestination

sealed class MainGraph(
    override val name: String,
    override val args: List<Args<Any>>,
) : Destination {

    data object Home : MainGraph(
        name = MAIN,
        args = listOf(IsTopLevelDestination(true))
    )

    data object Detail : MainGraph(
        name = DETAIL,
        args = listOf(Args.Detail)
    )

    companion object {
        private const val MAIN = "MAIN"
        private const val DETAIL = "DETAIL"
    }

}