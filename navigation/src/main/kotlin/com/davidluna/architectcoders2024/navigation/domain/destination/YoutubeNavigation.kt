package com.davidluna.architectcoders2024.navigation.domain.destination

import com.davidluna.architectcoders2024.navigation.domain.args.Args
import com.davidluna.architectcoders2024.navigation.domain.args.DefaultArgs
import com.davidluna.architectcoders2024.navigation.domain.args.SafeArgs

sealed class YoutubeNavigation(
    override val name: String,
    override val args: List<Pair<SafeArgs, Any?>> = emptyList(),
) : Destination {

    data object Init : YoutubeNavigation(name = INIT)

    data class Video(val movieId: Int? = null) : MediaNavigation(
        name = VIDEO_PLAYER,
        args = listOf(
            DefaultArgs.HideAppBar to DefaultArgs.HideAppBar.defaultValue,
            Args.DetailId to movieId
        ),
    )

    companion object {
        private const val INIT = "YOUTUBE_INIT"
        private const val VIDEO_PLAYER = "VIDEO_PLAYER"
    }
}