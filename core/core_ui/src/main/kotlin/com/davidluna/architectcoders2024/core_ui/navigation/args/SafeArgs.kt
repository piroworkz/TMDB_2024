package com.davidluna.architectcoders2024.core_ui.navigation.args

import androidx.navigation.NavType

sealed interface SafeArgs {
    val name: String
    val type: NavType<*>
}