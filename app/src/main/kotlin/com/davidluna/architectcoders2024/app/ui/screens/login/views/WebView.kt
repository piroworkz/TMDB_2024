package com.davidluna.architectcoders2024.app.ui.screens.login.views

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.webkit.WebViewClient
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun IntentView(token: String?, onDone: () -> Unit) {

    val resultLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                println("RESULT: ${result.data}")
            } else {
                println("RESULT: ${result.data}")
            }
        }

    val url =
        "https://www.themoviedb.org/authenticate/${token}?redirect_to=https://com.davidluna.architectcoders2024/"

    LaunchedEffect(key1 = Unit) {
        resultLauncher.launch(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        onDone()
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


class MyWebViewClient : WebViewClient() {

}