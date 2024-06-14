package com.davidluna.architectcoders2024.app.ui.screens.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.davidluna.architectcoders2024.app.ui.composables.AppBarView
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.app.utils.log

@Composable
fun VideoPlayerScreen(
    state: VideoPlayerViewModel.State,
    navigateUp: () -> Unit
) = with(rememberVideoPlayerState()) {

    SetScreenOrientation()
    AddLifecycleObserver()
    DisposableEffect(webView) {
        onDispose {
            webView.destroy()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.videos.isNotEmpty()) {
            AndroidView(
                factory = {
                    "factory called".log("VideoPlayerScreen")
                    webView.apply {
                        loadData(loadHtml(state.videos), "text/html", "UTF-8")
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            visible = showAppBar.value,
            enter = slideInVertically(tween(100)),
            exit = slideOutVertically(tween(100))
        ) {

            AppBarView(
                modifier = Modifier
                    .fillMaxWidth(),
                topLevel = false,
                hideAppBar = false,
                onNavigationIconClick = {
                    showAppBar.value = false
                    if (!showAppBar.value) {
                        navigateUp()
                    }
                }
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun VideoPlayerScreenPreview() {
    TmdbTheme {
        VideoPlayerScreen(
            state = VideoPlayerViewModel.State(),
        ) {}
    }
}
