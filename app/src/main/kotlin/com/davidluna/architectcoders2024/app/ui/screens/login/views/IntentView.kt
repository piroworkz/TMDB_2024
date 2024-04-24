package com.davidluna.architectcoders2024.app.ui.screens.login.views

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme

@Composable
fun IntentView(token: String?) {
    val context = LocalContext.current
    val url =
        if (token.isNullOrBlank()) "" else stringResource(R.string.intent_url, token)
    LaunchedEffect(key1 = token) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun WebViewPreview() {
    TmdbTheme {
        IntentView("")
    }
}