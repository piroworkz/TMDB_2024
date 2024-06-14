package com.davidluna.architectcoders2024.app.ui.theme.locals

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.fragment.app.FragmentActivity
import com.davidluna.architectcoders2024.app.MainActivity
import com.davidluna.architectcoders2024.app.ui.theme.DimensDp


@Composable
fun MainActivity.Locals(
    dimensDp: DimensDp,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalApplication provides this.application,
        LocalFragmentActivity provides this,
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

    val application: Application
        @Composable
        @ReadOnlyComposable
        get() = LocalApplication.current

    val fragmentActivity: FragmentActivity
        @Composable
        @ReadOnlyComposable
        get() = LocalFragmentActivity.current
}