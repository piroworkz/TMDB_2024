package com.davidluna.tmdb.videos_ui.view

import android.content.pm.ActivityInfo
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import com.davidluna.tmdb.core_ui.composables.AppBarView
import com.davidluna.tmdb.videos_ui.presenter.VideoPlayerViewModel

@Composable
fun VideoPlayerScreen(
    state: VideoPlayerViewModel.PlayerState,
    navigateUp: () -> Unit,
) = with(rememberVideoPlayerState()) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        AnimatedVisibility(
            visible = currentScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,
            enter = fadeIn(tween(1000)),
            exit = fadeOut(tween(1000))
        ) {
            AndroidView(
                factory = {
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
                topLevel = false,
                title = String(),
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
    com.davidluna.tmdb.core_ui.theme.TmdbTheme {
        VideoPlayerScreen(
            state = VideoPlayerViewModel.PlayerState(),
        ) {}
    }
}
