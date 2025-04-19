package com.davidluna.tmdb.media_ui.view.details.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.media_domain.entities.MediaDetails
import com.davidluna.tmdb.media_ui.view.media.composables.ReelTitleView

@Composable
fun MediaDetailsView(
    movieDetail: MediaDetails?,
    playTrailer: () -> Unit,
) {
    ReelTitleView(
        title = movieDetail?.title
    )

    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RectangleShape,
        colors = cardColors()
    ) {
        Spacer(modifier = Modifier.padding(all = Dimens.margins.medium))

        TextDetailsView(
            movieDetail = movieDetail
        )

        UserScoreView(
            hasVideo = movieDetail?.hasVideo ?: false,
            score = movieDetail?.voteAverage?.toFloat() ?: 0F,
            playTrailer = { playTrailer() }
        )
        Spacer(modifier = Modifier.padding(all = Dimens.margins.medium))
    }
}
