package com.davidluna.architectcoders2024.app.ui.screens.login.views

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme

@Composable
fun IntentView(token: String?, onDone: () -> Unit) {
    val context = LocalContext.current
    val url =
        "https://www.themoviedb.org/authenticate/${token}?redirect_to=https://tmdb.davidluna.com"

    LaunchedEffect(key1 = token) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
//        onDone()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun WebViewPreview() {
    TmdbTheme {
        IntentView("") {}
    }
}