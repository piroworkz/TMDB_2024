package com.davidluna.architectcoders2024.navigation.domain.args

import androidx.navigation.NavType
import com.davidluna.architectcoders2024.core_domain.entities.labels.NavArgument

sealed class DefaultArgs<out T>(
    override val name: String,
    override val type: NavType<*>,
    override val defaultValue: T
) : DefaultSafeArgs<T> {

    data object TopLevel : DefaultArgs<Boolean>(
        name = NavArgument.IS_TOP_LEVEL,
        type = NavType.BoolType,
        defaultValue = true
    )

    data object Auth : DefaultArgs<String>(
        name = NavArgument.APPROVED,
        type = NavType.StringType,
        defaultValue = "DENIED"
    )

    data object HideAppBar : DefaultArgs<Boolean>(
        name = NavArgument.HIDE_APP_BAR,
        type = NavType.BoolType,
        defaultValue = true
    )

}