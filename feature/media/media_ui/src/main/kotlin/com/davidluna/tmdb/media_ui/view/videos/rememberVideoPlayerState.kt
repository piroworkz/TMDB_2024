package com.davidluna.tmdb.media_ui.view.videos

import android.content.pm.ActivityInfo
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember

@Composable
fun rememberVideoPlayerState(
): VideoPlayerState {
    val activity = LocalActivity.current
    val state = remember(activity) { VideoPlayerState(activity) }

    DisposableEffect(activity) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            state.cleanUpWebView()
            state.exitFullscreen()
        }
    }

    return state
}