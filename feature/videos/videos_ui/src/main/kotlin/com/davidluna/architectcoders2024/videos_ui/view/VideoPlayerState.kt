package com.davidluna.architectcoders2024.videos_ui.view

import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner

class VideoPlayerState(
    private val application: Application,
    private val activity: Activity?,
    private val owner: LifecycleOwner,
    view: WebView
) {
    private val observer = VideoPlayerLifecycleObserver(activity)

    var showAppBar: MutableState<Boolean> = mutableStateOf(false)
        private set

    val webView: WebView = view.apply {
        setupJavascriptInterface()
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

    @Composable
    fun AddLifecycleObserver() {
        DisposableEffect(Unit) {
            owner.lifecycle.addObserver(observer)
            onDispose {
                owner.lifecycle.removeObserver(observer)
            }
        }
    }

    fun loadHtml(playlist: List<String> = emptyList()): String {
        val formattedList = playlist.map { "\'$it\'" }
        return application.assets.open(FILE_NAME).bufferedReader().use { it.readText() }
            .replace("{{playlist}}", formattedList.drop(1).joinToString(", "))
    }

    private fun WebView.setupJavascriptInterface() {
        addJavascriptInterface(
            object {
                @JavascriptInterface
                fun onPlayerStateChanged(event: Int) {
                    when (event) {
                        0 -> showAppBar.value = true
                        1 -> showAppBar.value = false
                        2 -> showAppBar.value = true
                        3 -> showAppBar.value = false
                        else -> showAppBar.value = true

                    }
                }
            }, JS_INTERFACE_NAME
        )
    }

    companion object {
        private const val FILE_NAME = "player.html"
        private const val JS_INTERFACE_NAME = "Android"
    }

}

