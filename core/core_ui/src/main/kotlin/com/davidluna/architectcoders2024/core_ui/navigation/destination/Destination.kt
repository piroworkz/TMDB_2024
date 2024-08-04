package com.davidluna.architectcoders2024.core_ui.navigation.destination

import com.davidluna.architectcoders2024.core_ui.navigation.args.SafeArgs

sealed interface Destination {
    val name: String
    val args: List<Pair<SafeArgs, Any?>>
}
