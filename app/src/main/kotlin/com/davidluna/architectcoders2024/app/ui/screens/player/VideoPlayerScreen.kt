package com.davidluna.architectcoders2024.app.ui.screens.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme

@Composable
fun VideoPlayerScreen(videoId: String) = with(rememberVideoPlayerState()) {
    SetScreenOrientation()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = {
                webView
            },
            modifier = Modifier
                .fillMaxSize(),
            update = { webView ->
                webView.loadData(loadHtml(videoId), "text/html", "UTF-8")
            }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun VideoPlayerScreenPreview() {
    TmdbTheme {
        VideoPlayerScreen(videoId = "Way9Dexny3w")
    }
}
