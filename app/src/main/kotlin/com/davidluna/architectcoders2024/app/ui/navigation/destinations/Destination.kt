package com.davidluna.architectcoders2024.app.ui.navigation.destinations

import androidx.navigation.NavDeepLink
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.SafeArgs

sealed interface Destination {
    val name: String
    val args: List<Pair<SafeArgs, Any?>>
    val deepLinks: List<NavDeepLink>
}
