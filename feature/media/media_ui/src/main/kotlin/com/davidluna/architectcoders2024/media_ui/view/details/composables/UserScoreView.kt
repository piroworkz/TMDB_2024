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
import androidx.compose.ui.res.stringResource
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.theme.locals.Locals

@Composable
fun UserScoreView(
    score: Float,
    playTrailer: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Locals.dimensDp.large),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { score / 10F },
                modifier = Modifier.size(Locals.dimensDp.large * 3),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            )
            Text(text = (score * 10F).toInt().toString().plus("%"))
        }

        Text(
            text = "User\nScore",
            modifier = Modifier.padding(Locals.dimensDp.large),
            style = MaterialTheme.typography.labelSmall
        )

        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = Locals.dimensDp.large),
            thickness = Locals.dimensDp.small,
            color = Color.White
        )

        TextButton(onClick = { playTrailer() }) {
            Icon(
                imageVector = Icons.Outlined.PlayArrow,
                contentDescription = Icons.Outlined.PlayArrow.name
            )

            Text(text = stringResource(R.string.play_trailer_button))

        }
    }
}