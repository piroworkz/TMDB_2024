package com.davidluna.architectcoders2024.videos_ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.davidluna.architectcoders2024.core_ui.theme.locals.Locals

@Composable
fun rememberVideoPlayerState(
    application: Application = Locals.application,
    activity: Activity? = Locals.fragmentActivity.get(),
    owner: LifecycleOwner = LocalLifecycleOwner.current,
    @SuppressLint("SetJavaScriptEnabled")
    webView: WebView = remember {
        WebView(activity!!).apply {
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
): VideoPlayerState = remember(webView) { VideoPlayerState(application, activity, owner, webView) }