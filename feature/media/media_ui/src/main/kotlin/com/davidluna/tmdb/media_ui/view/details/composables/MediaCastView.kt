package com.davidluna.tmdb.media_ui.view.details.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.composables.appGradient
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.media_domain.entities.Cast
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag
import com.davidluna.tmdb.media_ui.view.media.composables.ReelTitleView

@Composable
fun MediaCastView(
    cast: List<Cast>?,
) {
    val width = LocalConfiguration.current.screenWidthDp.dp

    ReelTitleView(
        modifier = Modifier.testTag(MediaTag.MEDIA_CAST_TITLE_VIEW),
        stringResource(R.string.title_cast_row)
    )

    cast?.let {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
                .testTag(MediaTag.MEDIA_CAST_VIEW)
        ) {

            items(cast.filter { it.profilePath.isNullOrEmpty().not() }) { cast ->
                Box(
                    modifier = Modifier
                        .padding(Dimens.margins.medium)
                        .width(width / 2.3F)
                        .clip(MaterialTheme.shapes.large)
                        .background(appGradient())
                        .testTag(MediaTag.CAST_BOX_CONTAINER),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = cast.profilePath,
                        contentDescription = "${cast.name} Picture",
                        modifier = Modifier.fillMaxWidth()
                            .testTag(MediaTag.CAST_IMAGE),
                        contentScale = ContentScale.FillWidth,
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .align(Alignment.BottomCenter)
                            .testTag(MediaTag.CAST_TITLE_BOX_CONTAINER),
                    ) {
                        Text(
                            text = "${cast.name} as ${cast.character}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dimens.margins.small)
                                .testTag(MediaTag.CAST_TITLE_TEXT),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}
