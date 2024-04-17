package com.davidluna.architectcoders2024.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

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
        popUpTo(route = destination.buildRoute()) { inclusive = false }
        launchSingleTop = true
    }
) {
    navigate(
        route = destination.buildRoute(),
        builder = { optionsBuilder() }
    )
}

private fun Destination.setNavArgs(): List<NamedNavArgument> =
    args.map { arguments: Args<Any> ->
        navArgument(arguments.name) {
            type = arguments.type
            arguments.defaultValue?.let { defaultValue = it }
        }
    }

fun Destination.route(): String =
    listOf(name)
        .plus(args.map { "{${it.name}}" })
        .joinToString(separator = "/")

private fun Destination.buildRoute(): String =
    listOf(name).plus(args.map { it.defaultValue.toString() }).joinToString(separator = "/")