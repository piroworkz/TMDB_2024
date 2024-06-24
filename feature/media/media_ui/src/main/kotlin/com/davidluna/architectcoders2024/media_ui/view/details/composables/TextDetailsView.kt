package com.davidluna.architectcoders2024.media_ui.view.details.composables

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
import com.davidluna.architectcoders2024.core_ui.theme.locals.Locals
import com.davidluna.media_domain.media_domain_entities.Details
import com.davidluna.media_domain.media_domain_entities.Genre
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TextDetailsView(movieDetail: Details?) {

    if (movieDetail == null) {
        CircularProgressIndicator()
    }

    Row(modifier = Modifier.padding(horizontal = Locals.dimensDp.large)) {
        Text(
            text = annotatedString(movieDetail),
        )
    }

    Text(
        text = movieDetail?.tagline ?: "",
        modifier = Modifier.padding(
            horizontal = Locals.dimensDp.large,
            vertical = Locals.dimensDp.medium
        ),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        fontStyle = FontStyle.Italic
    )

    Text(
        text = "Overview",
        modifier = Modifier.padding(
            horizontal = Locals.dimensDp.large,
            vertical = Locals.dimensDp.medium
        ),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Black
    )

    Text(
        text = movieDetail?.overview ?: "",
        modifier = Modifier.padding(horizontal = Locals.dimensDp.large),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        textAlign = TextAlign.Justify
    )
}

@Composable
private fun annotatedString(movieDetail: Details?) =
    AnnotatedString.Builder().apply {
        LabelStyle()
        movieDetail?.releaseDate?.let { append(formatDate(it)) }
        Bullet()
        movieDetail?.genres?.let { list: List<Genre> ->
            append(list.joinToString(separator = ", ") { it.name })
        }
    }.toAnnotatedString()


private fun formatDate(releaseDate: String): String {
    val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = originalFormat.parse(releaseDate)
    val localizedFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
    return localizedFormat.format(date ?: Date())
}