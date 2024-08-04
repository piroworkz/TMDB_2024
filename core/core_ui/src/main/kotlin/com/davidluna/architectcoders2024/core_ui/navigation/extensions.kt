package com.davidluna.architectcoders2024.core_ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.davidluna.architectcoders2024.core_ui.navigation.args.DefaultSafeArgs
import com.davidluna.architectcoders2024.core_ui.navigation.args.SafeArgs
import com.davidluna.architectcoders2024.core_ui.navigation.destination.DeepLink
import com.davidluna.architectcoders2024.core_ui.navigation.destination.Destination

fun NavGraphBuilder.composable(
    destination: Destination,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.route(),
        arguments = destination.setNavArgs(),
        deepLinks = if (destination is DeepLink) destination.deepLinks else emptyList()
    ) { backStackEntry: NavBackStackEntry ->
        content(backStackEntry)
    }
}

private fun Destination.setNavArgs(): List<NamedNavArgument> =
    args.map { arguments: Pair<SafeArgs, Any?> ->
        navArgument(arguments.first.name) {
            type = arguments.first.type
            if (arguments.first is DefaultSafeArgs<*>) {
                defaultValue = arguments.second
            }
        }
    }

fun Destination.route(): String = listOf(name)
    .plus(args.map { "{${it.first.name}}" })
    .joinToString(separator = "/")

fun Destination.setArgs(): String = listOf(name)
    .plus(args.map { it.second.toString() })
    .joinToString(separator = "/")