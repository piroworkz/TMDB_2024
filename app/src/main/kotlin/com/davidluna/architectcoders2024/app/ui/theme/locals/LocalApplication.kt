package com.davidluna.architectcoders2024.app.ui.theme.locals

import android.app.Application
import androidx.compose.runtime.compositionLocalOf
import androidx.fragment.app.FragmentActivity

val LocalApplication = compositionLocalOf<Application> {
    error("No Activity provided")
}

val LocalFragmentActivity = compositionLocalOf<FragmentActivity> {
    error("No Activity provided")
}