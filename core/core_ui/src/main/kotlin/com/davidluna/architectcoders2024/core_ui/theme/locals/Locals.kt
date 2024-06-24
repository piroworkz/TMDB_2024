package com.davidluna.architectcoders2024.core_ui.theme.locals

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.fragment.app.FragmentActivity
import com.davidluna.architectcoders2024.core_ui.theme.DimensDp
import java.lang.ref.WeakReference


@Composable
fun FragmentActivity.Locals(
    dimensDp: DimensDp,
    content: @Composable () -> Unit
) {
    val weakActivity = remember { WeakReference(this) }
    val application = remember { this.application }
    CompositionLocalProvider(
        LocalApplication provides application,
        LocalFragmentActivity provides weakActivity,
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

    val fragmentActivity: WeakReference<FragmentActivity>
        @Composable
        @ReadOnlyComposable
        get() = LocalFragmentActivity.current
}