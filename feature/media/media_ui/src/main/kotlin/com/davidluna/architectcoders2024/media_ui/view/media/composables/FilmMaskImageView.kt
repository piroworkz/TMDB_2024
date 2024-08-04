package com.davidluna.architectcoders2024.media_ui.view.media.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.composables.shimmer
import com.davidluna.architectcoders2024.core_ui.theme.dimens.Dimens.margins
import com.davidluna.architectcoders2024.media_domain.entities.tags.MediaTag.FILM_CIRCULAR_PROGRESS_INDICATOR
import com.davidluna.architectcoders2024.media_domain.entities.tags.MediaTag.FILM_IMAGE
import com.davidluna.architectcoders2024.media_domain.entities.tags.MediaTag.FILM_IMAGE_BOX_CONTAINER
import com.davidluna.architectcoders2024.media_domain.entities.tags.MediaTag.FILM_IMAGE_MASK

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
                    .shimmer(model.isNullOrEmpty())
                    .testTag(FILM_CIRCULAR_PROGRESS_INDICATOR),
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
