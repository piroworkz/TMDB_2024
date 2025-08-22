package com.davidluna.tmdb.media_ui.view.details.composables

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
import androidx.compose.ui.res.stringResource
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens

@Composable
fun UserScoreView(
    score: Float,
    playTrailer: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.margins.large),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                progress = { score / 10F },
                modifier = Modifier.size(Dimens.margins.large * 3),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            )
            Text(
                text = (score * 10F).toInt().toString().plus("%"),
                style = MaterialTheme.typography.labelMedium
            )
        }

        Text(
            text = stringResource(R.string.user_score_label),
            modifier = Modifier.padding(Dimens.margins.large),
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
        ) {
            Icon(
                imageVector = Icons.Outlined.PlayArrow,
                contentDescription = Icons.Outlined.PlayArrow.name,
            )

            Text(
                text = stringResource(R.string.play_trailer_button),
            )

        }
    }
}