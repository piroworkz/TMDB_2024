package com.davidluna.architectcoders2024.app.ui.navigation.safe_args

import androidx.navigation.NavType

sealed class Args(
    override val name: String,
    override val type: NavType<*>
) : SafeArgs {

    data object MovieId : Args(
        name = ID,
        type = NavType.IntType
    )

    companion object {
        private const val ID = "ID"
    }
}