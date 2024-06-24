package com.davidluna.architectcoders2024.media_ui.view.details.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.davidluna.architectcoders2024.media_ui.view.media.composables.ReelTitleView
import com.davidluna.media_domain.media_domain_entities.Details

@Composable
fun MovieDetailsView(
    movieDetail: Details?,
    playTrailer: () -> Unit
) {
    ReelTitleView(title = movieDetail?.title)
    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RectangleShape,
        colors = cardColors()
    ) {
        Spacer(modifier = Modifier.padding(all = com.davidluna.architectcoders2024.core_ui.theme.locals.Locals.dimensDp.medium))
        TextDetailsView(
            movieDetail = movieDetail
        )
        UserScoreView(
            score = movieDetail?.voteAverage?.toFloat() ?: 0F,
            playTrailer = { playTrailer() }
        )
        Spacer(modifier = Modifier.padding(all = com.davidluna.architectcoders2024.core_ui.theme.locals.Locals.dimensDp.medium))
    }
}
