package com.davidluna.architectcoders2024.app.ui.screens.master.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun MovieTitleView(movieTitle: String? = "Movie Title") {
    val imageSize = LocalConfiguration.current.screenWidthDp.dp / 2
    Text(
        text = movieTitle ?: "",
        modifier = Modifier
            .padding(8.dp)
            .sizeIn(maxWidth = imageSize),
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onPrimary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )
}