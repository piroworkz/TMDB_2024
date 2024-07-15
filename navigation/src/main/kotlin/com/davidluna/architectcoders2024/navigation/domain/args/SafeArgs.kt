package com.davidluna.architectcoders2024.navigation.domain.args

import androidx.navigation.NavType

sealed interface SafeArgs {
    val name: String
    val type: NavType<*>
}