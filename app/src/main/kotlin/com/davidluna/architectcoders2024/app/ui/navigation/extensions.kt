package com.davidluna.architectcoders2024.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.DeepLink
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultSafeArgs
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.SafeArgs
import com.davidluna.architectcoders2024.app.utils.log

fun NavGraphBuilder.setDestinationComposable(
    destination: Destination,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.route(),
        arguments = destination.setNavArgs(),
        deepLinks = if (destination is DeepLink) destination.deepLinks else emptyList()
    ) {
        content(it)
    }
}

fun NavGraphBuilder.setDestinationDialog(
    destination: Destination,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    dialog(
        route = destination.route(),
        arguments = destination.setNavArgs(),
        deepLinks = if (destination is DeepLink) destination.deepLinks else emptyList(),
        dialogProperties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        content(it)
    }
}


fun NavHostController.navigateTo(
    destination: Destination,
    optionsBuilder: (NavOptionsBuilder.() -> Unit) = {
        popUpTo(destination.route()) { inclusive = true }
    }
) {
    navigate(
        route = destination.setArgs(),
        builder = { optionsBuilder() }
    )
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