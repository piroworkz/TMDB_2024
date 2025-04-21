package com.davidluna.tmdb.media_ui.view.details.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.media_domain.entities.details.Cast
import com.davidluna.tmdb.media_ui.view.media.composables.ReelTitleView
import com.davidluna.tmdb.media_ui.view.media.composables.rememberItemWidth

@Composable
fun MediaCastView(
    cast: List<Cast>,
) {
    if (cast.isEmpty()) return
    val width = rememberItemWidth(3)
    if (cast.isNotEmpty()) {
        ReelTitleView(title = stringResource(R.string.title_cast_row))
    }
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {

        items(cast.filter { it.profilePath.isNotEmpty() }) { cast ->
            Box(
                modifier = Modifier
                    .padding(Dimens.margins.medium)
                    .width(width)
                    .clip(MaterialTheme.shapes.large),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(cast.profilePath)
                        .crossfade(1000)
                        .build(),
                    contentDescription = "${cast.name} Picture",
                    placeholder = painterResource(R.drawable.logo_v1),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(.66F),
                    contentScale = ContentScale.FillWidth,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .align(Alignment.BottomCenter),
                ) {
                    val text = if (cast.name.isNotEmpty() && cast.character.isNotEmpty()) {
                        "${cast.name} as ${cast.character}"
                    } else {
                        cast.name
                    }
                    Text(
                        text = text,
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

