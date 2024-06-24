package com.davidluna.architectcoders2024.core_ui.theme.locals

import android.app.Application
import androidx.compose.runtime.compositionLocalOf
import androidx.fragment.app.FragmentActivity
import java.lang.ref.WeakReference

val LocalApplication = compositionLocalOf<Application> {
    error("No Activity provided")
}

val LocalFragmentActivity = compositionLocalOf<WeakReference<FragmentActivity>> {
    error("No Activity provided")
}