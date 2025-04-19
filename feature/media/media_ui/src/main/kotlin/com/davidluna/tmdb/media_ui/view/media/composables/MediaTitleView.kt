package com.davidluna.tmdb.media_ui.view.media.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens

@Composable
fun MediaTitleView(movieTitle: String?, imageSize: Dp) {
    if (movieTitle == null) return
    Text(
        text = movieTitle,
        modifier = Modifier
            .padding(Dimens.margins.medium)
            .sizeIn(maxWidth = imageSize),
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onPrimary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )
}
