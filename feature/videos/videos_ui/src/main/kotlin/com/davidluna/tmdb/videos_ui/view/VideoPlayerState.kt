package com.davidluna.tmdb.videos_ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.davidluna.tmdb.videos_ui.R
import java.lang.ref.WeakReference
import kotlin.math.truncate

class VideoPlayerState(
    private val activity: WeakReference<Activity>,
) {

    lateinit var webView: WebView
        private set

    var showAppBar: MutableState<Boolean> = mutableStateOf(currentScreenOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        private set

    init {
        setupWebView(activity.get()!!)
    }

    private fun setupWebView(context: Context) {
        webView = buildWebView(context)
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun buildWebView(context: Context): WebView {
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
            setupJavascriptInterface()
        }
    }

    fun loadHtml(playlist: List<String> = emptyList()): String {
        val formattedList = playlist.map { "\'$it\'" }
        return activity.get()?.resources?.openRawResource(R.raw.player)
            ?.bufferedReader()
            .use { it?.readText() }
            ?.replace("{{playlist}}", formattedList.drop(1).joinToString(", ")) ?: String()
    }

    fun clearReferences() {
        webView.removeAllViews()
        webView.destroy()
        setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        activity.clear()
    }


    fun setScreenOrientation(orientation: Int) {
        activity.get()?.requestedOrientation = orientation
    }

    fun currentScreenOrientation(): Int? =
        activity.get()?.requestedOrientation

    fun Activity.hideSystemBars() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.run {
                hide(WindowInsets.Type.systemBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.apply {
                systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        )
            }
        }
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
        private const val JS_INTERFACE_NAME = "Android"
    }
}
