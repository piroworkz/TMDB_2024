package com.davidluna.architectcoders2024.app.ui.theme.locals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.davidluna.architectcoders2024.app.MainActivity
import com.davidluna.architectcoders2024.app.ui.theme.DimensDp


@Composable
fun MainActivity.Locals(
    dimensDp: DimensDp,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalActivity provides this,
        LocalDimensDp provides dimensDp,
    ) {
        content()
    }
}


object Locals {

    val dimensDp: DimensDp
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensDp.current

    val activity: MainActivity
        @Composable
        @ReadOnlyComposable
        get() = LocalActivity.current
}