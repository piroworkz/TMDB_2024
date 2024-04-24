package com.davidluna.architectcoders2024.app.ui.navigation.destinations

import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Args
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultArgs
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultArgs.HideAppBar
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultArgs.TopLevel
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.SafeArgs

sealed class MoviesGraph(
    override val name: String,
    override val args: List<Pair<SafeArgs, Any?>> = emptyList()
) : Destination {

    data object Init : MoviesGraph(
        name = INIT
    )

    data object Home : MoviesGraph(
        name = MAIN,
        args = listOf(TopLevel to TopLevel.defaultValue),
    )

    data class Detail(val movieId: Int? = null) : MoviesGraph(
        name = DETAIL,
        args = listOf(Args.DetailId to movieId),
    )

    data class VideoPlayer(val movieId: Int? = null) : MoviesGraph(
        name = VIDEO_PLAYER,
        args = listOf(HideAppBar to HideAppBar.defaultValue, Args.DetailId to movieId),
    )

    companion object {
        private const val INIT = "INIT"
        private const val MAIN = "MAIN"
        private const val DETAIL = "DETAIL"
        private const val VIDEO_PLAYER = "VIDEO_PLAYER"
    }

}