package com.davidluna.tmdb.media_ui.view.videos

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.davidluna.tmdb.media_domain.entities.details.Video
import com.davidluna.tmdb.media_ui.R

class VideoPlayerState(private val activity: Activity?) {

    private lateinit var webView: WebView

    val showAppBar: MutableState<Boolean> = mutableStateOf(true)

    @SuppressLint("SetJavaScriptEnabled")
    fun buildWebView(context: Context): WebView {
        webView = WebView(context).apply {
            settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
            }
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setupJavascriptInterface()
        }
        return webView
    }

    fun loadHtml(context: Context, videos: List<Video> = emptyList()): String {
        val first = videos.firstOrNull()?.key.orEmpty()
        val rest = if (videos.size > 1) videos.drop(1).joinToString(",") { it.key } else ""
        val rawHtml = context.resources?.openRawResource(R.raw.player)?.bufferedReader()
            ?.use { it.readText() } ?: return ""
        return rawHtml
            .replace("{{firstVideo}}", first)
            .replace("{{playlist}}", rest)
    }

    fun cleanUpWebView() {
        webView.removeAllViews()
        webView.destroy()
    }


    fun enterFullscreen() {
        activity?.hideSystemBars()
    }

    fun exitFullscreen() {
        activity?.showSystemBars()
    }

    private fun Activity.hideSystemBars() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let {
                it.hide(WindowInsets.Type.systemBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    )
        }
    }

    private fun Activity.showSystemBars() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.show(WindowInsets.Type.systemBars())
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }

    private fun WebView.setupJavascriptInterface() {
        addJavascriptInterface(
            object {
                @Suppress("unused")
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
            }, "Android"
        )
    }
}
