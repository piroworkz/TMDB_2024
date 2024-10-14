package com.davidluna.tmdb.media_ui.view.media.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.composables.shimmer
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens.margins
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.FILM_IMAGE
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.FILM_IMAGE_BOX_CONTAINER
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.FILM_IMAGE_MASK

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
            )
            .testTag(FILM_IMAGE_BOX_CONTAINER),
        contentAlignment = Alignment.Center
    ) {
        if (model.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .size(imageSize)
                    .shimmer(model.isNullOrEmpty()),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            AsyncImage(
                model = model,
                contentDescription = "film reel",
                modifier = Modifier.fillMaxSize()
                    .testTag(FILM_IMAGE),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )
        }

        Image(
            painter = painterResource(id = R.drawable.film_mask),
            contentDescription = "film reel",
            modifier = Modifier
                .fillMaxSize()
                .testTag(FILM_IMAGE_MASK),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun LoadingImageView(
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
            )
            .shimmer(true)
            .testTag(FILM_IMAGE_BOX_CONTAINER),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.film_mask),
            contentDescription = "film reel",
            modifier = Modifier
                .fillMaxSize()
                .testTag(FILM_IMAGE_MASK),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun ErrorImageView(
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
            )
            .testTag(FILM_IMAGE_BOX_CONTAINER),
        contentAlignment = Alignment.Center
    ) {

        Text(
            "Image not found",
            modifier = Modifier
                .wrapContentSize()
                .width(imageSize)
                .align(Alignment.Center),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(id = R.drawable.film_mask),
            contentDescription = "film reel",
            modifier = Modifier
                .fillMaxSize()
                .testTag(FILM_IMAGE_MASK),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
private fun Preview() {
    TmdbTheme {
        ErrorImageView(200.dp)
    }
}

