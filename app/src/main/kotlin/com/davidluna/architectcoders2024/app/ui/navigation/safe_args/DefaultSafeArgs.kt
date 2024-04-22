package com.davidluna.architectcoders2024.app.ui.navigation.safe_args

sealed interface DefaultSafeArgs<out T> : SafeArgs {
    val defaultValue: T
}

