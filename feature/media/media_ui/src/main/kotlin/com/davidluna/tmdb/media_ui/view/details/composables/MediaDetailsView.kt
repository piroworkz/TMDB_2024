package com.davidluna.tmdb.media_ui.view.details.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.media_domain.entities.details.Genre

@Composable
fun MediaDetailsView(
    releaseDate: String?,
    genres: List<Genre>?,
    hasVideo: Boolean,
    tagline: String,
    overview: String,
    voteAverage: Float,
    playTrailer: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Spacer(modifier = Modifier.padding(all = Dimens.margins.medium))

        Text(
            text = annotatedString(
                releaseDate = releaseDate,
                genres = genres
            ),
            modifier = Modifier
                .padding(horizontal = Dimens.margins.large)
        )

        Text(
            text = tagline,
            modifier = Modifier.padding(
                horizontal = Dimens.margins.large,
                vertical = Dimens.margins.medium
            ),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            fontStyle = FontStyle.Italic
        )

        Text(
            text = stringResource(R.string.title_overview),
            modifier = Modifier
                .padding(
                    horizontal = Dimens.margins.large,
                    vertical = Dimens.margins.medium
                ),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Black
        )

        Text(
            text = overview,
            modifier = Modifier
                .padding(horizontal = Dimens.margins.large),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            textAlign = TextAlign.Justify
        )

        UserScoreView(
            score = voteAverage,
            hasVideo = hasVideo,
            playTrailer = { playTrailer() }
        )

        Spacer(modifier = Modifier.padding(all = Dimens.margins.medium))

    }
}

@Composable
private fun annotatedString(
    releaseDate: String?,
    genres: List<Genre>?,
) =
    AnnotatedString.Builder().apply {
        LabelStyle()
        releaseDate?.let { append(it) }
        Bullet()
        genres?.let { list ->
            append(list.joinToString(separator = ", ") { it.name })
        }
    }.toAnnotatedString()