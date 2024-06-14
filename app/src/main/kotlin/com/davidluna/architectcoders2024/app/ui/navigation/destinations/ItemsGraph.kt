package com.davidluna.architectcoders2024.app.ui.navigation.destinations

import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Args
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Args.MovieId
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.ContentType
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultArgs.HideAppBar
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultArgs.TopLevel
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.SafeArgs

sealed class ItemsGraph(
    override val name: String,
    override val args: List<Pair<SafeArgs, Any?>> = emptyList()
) : Destination {

    data object Init : ItemsGraph(
        name = INIT
    )

    data class Home(
        val contentType: ContentType? = ContentType.MOVIE
    ) : ItemsGraph(
        name = MAIN,
        args = listOf(
            TopLevel to TopLevel.defaultValue,
            Args.Type to contentType
        )
    )

    data class Detail(
        val movieId: Int? = null,
        val contentType: ContentType? = ContentType.MOVIE
    ) : ItemsGraph(
        name = DETAIL,
        args = listOf(
            MovieId to movieId,
            Args.Type to contentType
        )
    )

    data class VideoPlayer(
        val movieId: Int? = null,
        val contentType: ContentType? = ContentType.MOVIE
    ) : ItemsGraph(
        name = VIDEO_PLAYER,
        args = listOf(
            HideAppBar to HideAppBar.defaultValue,
            MovieId to movieId,
            Args.Type to contentType
        ),
    )

    companion object {
        private const val INIT = "INIT"
        private const val MAIN = "MAIN"
        private const val DETAIL = "DETAIL"
        private const val VIDEO_PLAYER = "VIDEO_PLAYER"
    }

}