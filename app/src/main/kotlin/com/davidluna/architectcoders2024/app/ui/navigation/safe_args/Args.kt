package com.davidluna.architectcoders2024.app.ui.navigation.safe_args

import androidx.navigation.NavType

sealed class Args(
    override val name: String,
    override val type: NavType<*>
) : SafeArgs {

    data object Type : Args(
        name = "CONTENT_TYPE",
        type = NavType.EnumType(ContentType::class.java)
    )

    data object LoadMovies : Args(
        name = ITEM_TYPE,
        type = NavType.BoolType
    )

    data object MovieId : Args(
        name = ID,
        type = NavType.IntType
    )

    companion object {
        private const val ID = "ID"
        private const val ITEM_TYPE = "ITEM_TYPE"
    }
}

enum class ContentType {
    MOVIE, TV
}