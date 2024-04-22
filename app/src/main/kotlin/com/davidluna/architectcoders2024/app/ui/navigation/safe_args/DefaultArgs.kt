package com.davidluna.architectcoders2024.app.ui.navigation.safe_args

import androidx.navigation.NavType

sealed class DefaultArgs<out T>(
    override val name: String,
    override val type: NavType<*>,
    override val defaultValue: T
) : DefaultSafeArgs<T> {

    data object TopLevel : DefaultArgs<Boolean>(
        name = IS_TOP_LEVEL,
        type = NavType.BoolType,
        defaultValue = true
    )

    data object Auth : DefaultArgs<String>(
        name = APPROVED,
        type = NavType.StringType,
        defaultValue = "DENIED"
    )

    data object HideAppBar : DefaultArgs<Boolean>(
        name = HIDE_APP_BAR,
        type = NavType.BoolType,
        defaultValue = true
    )

    companion object {
        private const val IS_TOP_LEVEL = "IS_TOP_LEVEL"
        private const val APPROVED = "APPROVED"
        private const val HIDE_APP_BAR = "HIDE_APP_BAR"
    }
}