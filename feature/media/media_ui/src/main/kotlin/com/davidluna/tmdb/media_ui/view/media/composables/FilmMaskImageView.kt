package com.davidluna.tmdb.media_ui.view.media.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.composables.shimmer
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens.margins

@Composable
fun FilmMaskImageView(
    model: String?,
    imageSize: Dp,
) {

    Box(
        modifier = Modifier
            .wrapContentSize()
            .size(imageSize)
            .sizeIn(
                minWidth = margins.minImageSize,
                minHeight = margins.minImageSize,
                maxWidth = imageSize,
                maxHeight = imageSize
            ),
        contentAlignment = Alignment.Center
    ) {
        if (model.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .size(imageSize)
                    .shimmer(model.isNullOrEmpty()),
                contentAlignment = Alignment.Center
            ) {
            }
        } else {
            AsyncImage(
                model = model,
                contentDescription = "film reel",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )
        }

        Image(
            painter = painterResource(id = R.drawable.film_mask),
            contentDescription = "film reel",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}


@Preview
@Composable
private fun FilmMaskImagePreview() {
    TmdbTheme {
        FilmMaskImageView(
            model = null,
            imageSize = 200.dp
        )
    }
}