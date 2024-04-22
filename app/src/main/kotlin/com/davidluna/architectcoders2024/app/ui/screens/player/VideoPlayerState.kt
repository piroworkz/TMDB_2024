package com.davidluna.architectcoders2024.app.ui.screens.player

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

class VideoPlayerState(
    private val context: Context,
    private val activity: Activity?
) {


    val webView: WebView
        @SuppressLint("SetJavaScriptEnabled")
        get() {
            return WebView(context).apply {
                settings.apply {
                    javaScriptEnabled = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                }
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        }


    @Composable
    fun SetScreenOrientation() {
        DisposableEffect(Unit) {
            activity?.requestedOrientation =
                SCREEN_ORIENTATION_LANDSCAPE
            onDispose {
                activity?.requestedOrientation =
                    SCREEN_ORIENTATION_UNSPECIFIED
            }
        }
    }

    fun loadHtml(videoId: String): String {
        return context.assets.open("player.html").bufferedReader().use { it.readText() }
            .replace("{{videoId}}", videoId)
    }


}


@Composable
fun rememberVideoPlayerState(
    context: Context = LocalContext.current,
    activity: Activity? = context.getParentActivity(),
) = remember { VideoPlayerState(context, activity) }


fun Context.getParentActivity(): Activity? {
    return when (this) {
        is Activity -> this
        else -> null
    }
}