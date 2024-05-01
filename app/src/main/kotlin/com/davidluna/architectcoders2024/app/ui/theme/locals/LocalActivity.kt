package com.davidluna.architectcoders2024.app.ui.theme.locals

import androidx.compose.runtime.compositionLocalOf
import com.davidluna.architectcoders2024.app.MainActivity

val LocalActivity = compositionLocalOf<MainActivity> {
    error("No Activity provided")
}