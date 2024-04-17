package com.davidluna.architectcoders2024.app.ui.navigation

import androidx.navigation.NavArgs
import androidx.navigation.NavType

sealed class Args<out T>(
    val name: String,
    val type: NavType<*>,
    val defaultValue: T? = null
) : NavArgs {

    data class IsTopLevelDestination(
        val value: Boolean = true
    ) : Args<Boolean>(
        name = IS_TOP_LEVEL_DESTINATION,
        type = NavType.BoolType,
        defaultValue = value
    )

    data object Detail : Args<Int>(
        name = ID,
        type = NavType.IntType,
        defaultValue = null
    )

    data object Authentication : Args<String>(
        name = APPROVED,
        type = NavType.StringType,
        defaultValue = "denied"
    )

    companion object {
        private const val ID = "ID"
        private const val IS_TOP_LEVEL_DESTINATION = "isTopLevelDestination"
        private const val APPROVED = "approved"
    }

}