package com.davidluna.tmdb.media_ui.view.details.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import com.davidluna.tmdb.core_ui.composables.shimmer
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.media_domain.entities.MediaDetails
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAILS_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAILS_VIEW
import com.davidluna.tmdb.media_ui.view.media.composables.ReelTitleView

@Composable
fun MediaDetailsView(
    movieDetail: MediaDetails?,
    playTrailer: () -> Unit,
) {
    ReelTitleView(
        modifier = Modifier.testTag(MEDIA_DETAILS_TITLE_VIEW),
        title = movieDetail?.title
    )

    AnimatedContent(movieDetail != null, label = "MediaDetailsView") {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(MEDIA_DETAILS_VIEW),
            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (it) {
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
            } else {
                LoadingMediaDetails()
            }
        }
    }

}

@Composable
fun LoadingMediaDetails() {

    Spacer(modifier = Modifier.padding(all = Dimens.margins.medium))

    LoadingBox(height = Dimens.margins.medium)
    Spacer(modifier = Modifier.padding(all = Dimens.margins.medium))

    LoadingBox(height = Dimens.margins.medium)
    Spacer(modifier = Modifier.padding(all = Dimens.margins.medium))

    LoadingBox(
        modifier = Modifier.width(Dimens.margins.xxxLarge),
        height = Dimens.margins.medium
    )
    Spacer(modifier = Modifier.padding(all = Dimens.margins.large))

    LoadingBox(height = Dimens.margins.xxxLarge)
    Spacer(modifier = Modifier.padding(all = Dimens.margins.medium))

    LoadingBox(height = Dimens.margins.xxLarge)
    Spacer(modifier = Modifier.padding(all = Dimens.margins.medium))


}

@Composable
fun LoadingBox(
    modifier: Modifier = Modifier,
    height: Dp,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.margins.medium)
            .height(height)
            .clip(MaterialTheme.shapes.medium)
            .alpha(0.5F)
            .shimmer(true)
    )
}