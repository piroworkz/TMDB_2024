package com.davidluna.architectcoders2024.media_ui.view.details.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.theme.dimens.Dimens
import com.davidluna.architectcoders2024.media_domain.entities.tags.MediaTag

@Composable
fun UserScoreView(
    hasVideo: Boolean,
    score: Float,
    playTrailer: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.margins.large)
            .testTag(MediaTag.USER_SCORE_VIEW),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .testTag(MediaTag.USER_SCORE_BOX_CONTAINER)
        ) {
            CircularProgressIndicator(
                progress = { score / 10F },
                modifier = Modifier.size(Dimens.margins.large * 3)
                    .testTag(MediaTag.USER_SCORE_PROGRESS),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            )
            Text(
                text = (score * 10F).toInt().toString().plus("%"),
                modifier = Modifier
                    .testTag(MediaTag.USER_SCORE_PERCENTAGE),
                style = MaterialTheme.typography.labelMedium
            )
        }

        Text(
            text = stringResource(R.string.user_score_label),
            modifier = Modifier.padding(Dimens.margins.large)
                .testTag(MediaTag.USER_SCORE_LABEL),
            style = MaterialTheme.typography.labelSmall
        )

        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = Dimens.margins.large),
            thickness = Dimens.margins.small,
            color = Color.White
        )

        TextButton(
            onClick = { playTrailer() },
            modifier = Modifier
                .testTag(MediaTag.PLAY_TRAILER_BUTTON),
            enabled = hasVideo
        ) {
            Icon(
                imageVector = Icons.Outlined.PlayArrow,
                contentDescription = Icons.Outlined.PlayArrow.name,
                modifier = Modifier.testTag(MediaTag.PLAY_TRAILER_BUTTON_ICON)
            )

            Text(
                text = stringResource(R.string.play_trailer_button),
                modifier = Modifier.testTag(MediaTag.PLAY_TRAILER_BUTTON_TEXT)
            )

        }
    }
}