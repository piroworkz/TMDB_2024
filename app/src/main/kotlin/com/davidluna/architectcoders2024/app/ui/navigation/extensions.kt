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
        destination.route(),
        arguments = destination.setNavArgs(),
    ) {
        content(it)
    }
}

fun <T> NavHostController.navigateTo(
    destination: Destination,
    args: T? = null,
    optionsBuilder: (NavOptionsBuilder.() -> Unit) = {
        popUpTo(route = destination.buildRoute(args?.toString())) { inclusive = false }
        launchSingleTop = true
    }
) {
    navigate(
        route = destination.buildRoute(args?.toString()),
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

private fun Destination.buildRoute(value: String?): String =
    if (value != null) listOf(name).plus(args.map { value }).joinToString(separator = "/") else name