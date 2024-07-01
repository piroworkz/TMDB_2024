package com.davidluna.architectcoders2024.videos_ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.davidluna.architectcoders2024.core_ui.composables.AppBarView
import com.davidluna.architectcoders2024.core_ui.composables.SlideFromTopAnimation
import com.davidluna.architectcoders2024.videos_ui.presenter.PlayerState

@Composable
fun VideoPlayerScreen(
    state: PlayerState,
    navigateUp: () -> Unit
) = with(rememberVideoPlayerState()) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SlideFromTopAnimation(
            target = state.videos.isNotEmpty(),
            modifier = Modifier.fillMaxSize(),
            label = "VideoPlayerContent"
        ) { target: Boolean ->
            if (target) {
                AndroidView(
                    factory = {
                        webView.apply {
                            loadData(loadHtml(state.videos), "text/html", "UTF-8")
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
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
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun VideoPlayerScreenPreview() {
    com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme {
        VideoPlayerScreen(
            state = PlayerState(),
        ) {}
    }
}
