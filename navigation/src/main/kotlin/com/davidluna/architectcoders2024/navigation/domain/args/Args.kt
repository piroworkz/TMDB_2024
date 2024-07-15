package com.davidluna.architectcoders2024.navigation.domain.args

import androidx.navigation.NavType
import com.davidluna.architectcoders2024.core_domain.core_entities.labels.NavArgument.APP_BAR_TITLE
import com.davidluna.architectcoders2024.core_domain.core_entities.labels.NavArgument.MEDIA_ID

sealed class Args(
    override val name: String,
    override val type: NavType<*>
) : SafeArgs {

    data object DetailId : Args(
        name = MEDIA_ID,
        type = NavType.IntType
    )

    data object AppBarTitle : Args(
        name = APP_BAR_TITLE,
        type = NavType.StringType
    )

}