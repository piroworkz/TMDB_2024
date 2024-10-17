package com.davidluna.tmdb.media_ui.view.video_player

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.davidluna.tmdb.core_ui.composables.AppBarView
import com.davidluna.tmdb.media_domain.entities.tags.PlayerTag.PLAYER_ANIMATED_VISIBILITY
import com.davidluna.tmdb.media_domain.entities.tags.PlayerTag.PLAYER_APP_BAR
import com.davidluna.tmdb.media_domain.entities.tags.PlayerTag.PLAYER_APP_BAR_ANIMATED_VISIBILITY
import com.davidluna.tmdb.media_domain.entities.tags.PlayerTag.PLAYER_WEB_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.PlayerTag.VIDEO_PLAYER_SCREEN
import com.davidluna.tmdb.media_ui.presenter.video_player.VideoPlayerViewModel

@Composable
fun VideoPlayerScreen(
    state: VideoPlayerViewModel.PlayerState,
    navigateUp: () -> Unit,
) = with(rememberVideoPlayerState()) {
    Box(
        modifier = Modifier.fillMaxSize()
            .testTag(VIDEO_PLAYER_SCREEN),
        contentAlignment = Alignment.Center
    ) {

        AnimatedVisibility(
            visible = currentScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,
            modifier = Modifier
                .testTag(PLAYER_ANIMATED_VISIBILITY),
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
                    .testTag(PLAYER_WEB_VIEW)
            )
        }


        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .testTag(PLAYER_APP_BAR_ANIMATED_VISIBILITY),
            visible = showAppBar.value,
            enter = slideInVertically(tween(100)),
            exit = slideOutVertically(tween(100))
        ) {

            AppBarView(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(PLAYER_APP_BAR),
                topLevel = false,
                hideAppBar = false,
                title = null,
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
