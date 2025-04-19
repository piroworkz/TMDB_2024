package com.davidluna.tmdb.videos_ui.view

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import com.davidluna.tmdb.core_ui.composables.findActivity
import java.lang.ref.WeakReference

@Composable
fun rememberVideoPlayerState(
    context: Context = LocalContext.current,
    activity: WeakReference<Activity> = remember { WeakReference(context.findActivity()) }
): VideoPlayerState {

    val state = remember(context) { VideoPlayerState(activity) }

    DisposableEffect(context) {
        state.apply {
            setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            activity.get()?.let { mainActivity: Activity ->
               mainActivity.hideSystemBars()
            }
        }

        onDispose {
            state.clearReferences()
        }
    }

    return state
}