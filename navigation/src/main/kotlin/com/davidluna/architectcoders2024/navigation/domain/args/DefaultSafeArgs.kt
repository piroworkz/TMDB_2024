package com.davidluna.architectcoders2024.navigation.domain.args

sealed interface DefaultSafeArgs<out T> : SafeArgs {
    val defaultValue: T
}
