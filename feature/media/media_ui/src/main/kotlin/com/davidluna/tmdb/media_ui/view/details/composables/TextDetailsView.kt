package com.davidluna.tmdb.media_ui.view.details.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.media_domain.entities.Genre
import com.davidluna.tmdb.media_domain.entities.MediaDetails

@Composable
fun TextDetailsView(movieDetail: MediaDetails?) {

    if (movieDetail == null) {
        CircularProgressIndicator()
    }

    Text(
        text = annotatedString(movieDetail),
        modifier = Modifier
            .padding(horizontal = Dimens.margins.large)
    )

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
        text = stringResource(R.string.title_overview),
        modifier = Modifier
            .padding(
                horizontal = Dimens.margins.large,
                vertical = Dimens.margins.medium
            ),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Black
    )

    Text(
        text = movieDetail?.overview ?: "",
        modifier = Modifier
            .padding(horizontal = Dimens.margins.large),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        textAlign = TextAlign.Justify
    )
}

@Composable
private fun annotatedString(movieDetail: MediaDetails?) =
    AnnotatedString.Builder().apply {
        LabelStyle()
        movieDetail?.releaseDate?.let { append(it) }
        Bullet()
        movieDetail?.genres?.let { list: List<Genre> ->
            append(list.joinToString(separator = ", ") { it.name })
        }
    }.toAnnotatedString()


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

val fakeDetails = MediaDetails(
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