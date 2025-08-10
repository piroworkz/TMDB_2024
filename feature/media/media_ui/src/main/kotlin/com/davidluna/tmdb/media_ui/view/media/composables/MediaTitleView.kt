package com.davidluna.tmdb.media_ui.view.media.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens

@Composable
fun MediaTitleView(movieTitle: String?) {
    Text(
        text = movieTitle.orEmpty(),
        modifier = Modifier
            .padding(horizontal = Dimens.margins.small)
            .fillMaxWidth(),
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.primary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )
}
