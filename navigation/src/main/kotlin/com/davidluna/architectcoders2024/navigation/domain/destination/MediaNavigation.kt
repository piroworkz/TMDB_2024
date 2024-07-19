package com.davidluna.architectcoders2024.navigation.domain.destination

import com.davidluna.architectcoders2024.navigation.domain.args.Args
import com.davidluna.architectcoders2024.navigation.domain.args.DefaultArgs
import com.davidluna.architectcoders2024.navigation.domain.args.SafeArgs

sealed class MediaNavigation(
    override val name: String,
    override val args: List<Pair<SafeArgs, Any?>> = emptyList()
) : Destination {

    data object Init : MediaNavigation(
        name = INIT
    )

    data object MediaCatalog : MediaNavigation(
        name = MAIN,
        args = listOf(DefaultArgs.TopLevel to DefaultArgs.TopLevel.defaultValue)
    )

    data class Detail(val movieId: Int? = null, val appBarTitle: String?) : MediaNavigation(
        name = DETAIL,
        args = listOf(
            Args.DetailId to movieId,
            Args.AppBarTitle to appBarTitle
        )
    )

    companion object {
        private const val INIT = "INIT"
        private const val MAIN = "MAIN"
        private const val DETAIL = "DETAIL"
    }

}