package com.davidluna.architectcoders2024.app.ui.navigation.safe_args

import androidx.navigation.NavType

sealed interface SafeArgs {
    val name: String
    val type: NavType<*>
}
