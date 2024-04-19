package com.davidluna.architectcoders2024.app.ui.navigation.safe_args

sealed interface ByDefault<out T> : SafeArgs {
    val defaultValue: T
}

