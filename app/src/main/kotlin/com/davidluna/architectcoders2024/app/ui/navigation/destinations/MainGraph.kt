package com.davidluna.architectcoders2024.app.ui.navigation.destinations

import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Args
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultArgs.TopLevel
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.SafeArgs

sealed class MainGraph(
    override val name: String,
    override val args: List<Pair<SafeArgs, Any?>> = emptyList()
) : Destination {

    data object Init : MainGraph(
        name = INIT
    )

    data object Home : MainGraph(
        name = MAIN,
        args = listOf(TopLevel to TopLevel.defaultValue),
    )

    data class Detail(val movieId: Int? = null) : MainGraph(
        name = DETAIL,
        args = listOf(Args.DetailId to movieId),
    )

    data class VideoPlayer(val movieId: Int? = null) : MainGraph(
        name = VIDEO_PLAYER,
        args = listOf(Args.DetailId to movieId),
    )

    companion object {
        private const val INIT = "INIT"
        private const val MAIN = "MAIN"
        private const val DETAIL = "DETAIL"
        private const val VIDEO_PLAYER = "VIDEO_PLAYER"
    }

}