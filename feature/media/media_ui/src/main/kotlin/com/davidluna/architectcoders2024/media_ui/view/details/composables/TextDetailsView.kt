package com.davidluna.architectcoders2024.media_ui.view.details.composables

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.core_ui.theme.dimens.Dimens
import com.davidluna.media_domain.media_domain_entities.Details
import com.davidluna.media_domain.media_domain_entities.Genre

@Composable
fun TextDetailsView(movieDetail: Details?) {

    if (movieDetail == null) {
        CircularProgressIndicator()
    }

    Row(modifier = Modifier.padding(horizontal = Dimens.margins.large)) {
        Text(
            text = annotatedString(movieDetail),
        )
    }

    Text(
        text = movieDetail?.tagline ?: "",
        modifier = Modifier.padding(
            horizontal = Dimens.margins.large,
            vertical = Dimens.margins.medium
        ),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        fontStyle = FontStyle.Italic
    )

    Text(
        text = "Overview",
        modifier = Modifier.padding(
            horizontal = Dimens.margins.large,
            vertical = Dimens.margins.medium
        ),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Black
    )

    Text(
        text = movieDetail?.overview ?: "",
        modifier = Modifier.padding(horizontal = Dimens.margins.large),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        textAlign = TextAlign.Justify
    )
}

@Composable
private fun annotatedString(movieDetail: Details?) =
    AnnotatedString.Builder().apply {
        LabelStyle()
        movieDetail?.releaseDate?.let { append(it) }
        Bullet()
        movieDetail?.genres?.let { list: List<Genre> ->
            append(list.joinToString(separator = ", ") { it.name })
        }
    }.toAnnotatedString()

private fun String.log() {
    Log.d("<-- ", this)
}


@Preview
@Composable
private fun TextDetailsPreView() {
    TmdbTheme {
        Column {
            TextDetailsView(
                movieDetail = fakeDetails
            )
        }
    }
}

val fakeDetails = Details(
    id = 1,
    title = "Movie Title",
    overview = "Movie Overview",
    tagline = "Movie Tagline",
    releaseDate = "2022-01-01",
    genres = listOf(Genre(1, "Action"), Genre(2, "Adventure")),
    voteAverage = 5.0,
    hasVideo = false,
    posterPath = "posterPath",
)