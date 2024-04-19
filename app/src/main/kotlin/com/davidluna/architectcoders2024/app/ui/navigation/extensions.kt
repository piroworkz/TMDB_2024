package com.davidluna.architectcoders2024.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.ByDefault
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.SafeArgs

fun NavGraphBuilder.navComposable(
    destination: Destination,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.route(),
        arguments = destination.setNavArgs(),
        deepLinks = destination.deepLinks
    ) {
        content(it)
    }
}

fun NavHostController.navigateTo(
    destination: Destination,
    optionsBuilder: (NavOptionsBuilder.() -> Unit) = {
        popUpTo(route = destination.setArgs()) { inclusive = false }
        launchSingleTop = true
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
            if (arguments.first is ByDefault<*>) {
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