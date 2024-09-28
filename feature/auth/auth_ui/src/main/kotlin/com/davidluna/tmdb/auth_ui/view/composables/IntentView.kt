package com.davidluna.tmdb.auth_ui.view.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.davidluna.tmdb.core_ui.R

@Composable
fun IntentView(token: String?) {
    if (token.isNullOrBlank()) return
    val context = LocalContext.current
    val url = stringResource(R.string.intent_url, token)
    LaunchedEffect(key1 = token) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}
