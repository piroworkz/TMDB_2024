package com.davidluna.architectcoders2024.app.ui.navigation

sealed interface Destination {
    val name: String
    val args: List<Args<Any>>
}


