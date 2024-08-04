package com.davidluna.architectcoders2024.media_ui.view.media.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.davidluna.architectcoders2024.core_ui.theme.dimens.Dimens
import com.davidluna.architectcoders2024.media_domain.entities.tags.MediaTag.REEL_TITLE_TEXT
import com.davidluna.architectcoders2024.media_domain.entities.tags.MediaTag.REEL_TITLE_VIEW

@Composable
fun ReelTitleView(title: String?) {
    title?.let {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .testTag(REEL_TITLE_VIEW)

        ) {
            Text(
                text = title,
                modifier = Modifier
                    .padding(Dimens.margins.small)
                    .fillMaxWidth()
                    .testTag(REEL_TITLE_TEXT),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        }
    }
}
