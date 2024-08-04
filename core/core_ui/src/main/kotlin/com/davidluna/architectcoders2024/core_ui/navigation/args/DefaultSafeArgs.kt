package com.davidluna.architectcoders2024.core_ui.navigation.args

sealed interface DefaultSafeArgs<out T> : SafeArgs {
    val defaultValue: T
}
