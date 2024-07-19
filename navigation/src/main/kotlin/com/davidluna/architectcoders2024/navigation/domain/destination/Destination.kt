package com.davidluna.architectcoders2024.navigation.domain.destination

import com.davidluna.architectcoders2024.navigation.domain.args.SafeArgs

sealed interface Destination {
    val name: String
    val args: List<Pair<SafeArgs, Any?>>
}
