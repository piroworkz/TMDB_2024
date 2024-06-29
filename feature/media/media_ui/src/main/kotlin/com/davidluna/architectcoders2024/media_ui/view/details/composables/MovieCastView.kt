package com.davidluna.architectcoders2024.media_ui.view.details.composables

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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidluna.architectcoders2024.core_ui.composables.appGradient
import com.davidluna.architectcoders2024.core_ui.theme.dimens.Dimens
import com.davidluna.architectcoders2024.media_ui.view.media.composables.ReelTitleView
import com.davidluna.media_domain.media_domain_entities.Cast

@Composable
fun MovieCastView(
    cast: List<Cast>?,
) {
    val width = LocalConfiguration.current.screenWidthDp.dp

    ReelTitleView("CAST")
    cast?.let {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {

            items(cast.filter { it.profilePath.isNullOrEmpty().not() }) { cast ->
                Box(
                    modifier = Modifier
                        .padding(Dimens.margins.medium)
                        .width(width / 2.3F)
                        .clip(MaterialTheme.shapes.large)
                        .background(appGradient()),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = cast.profilePath,
                        contentDescription = "${cast.name} Picture",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth,
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .align(Alignment.BottomCenter)
                    ) {
                        Text(
                            text = "${cast.name} as ${cast.character}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dimens.margins.small),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}

