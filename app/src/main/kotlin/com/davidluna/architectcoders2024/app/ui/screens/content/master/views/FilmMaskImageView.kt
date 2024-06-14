package com.davidluna.architectcoders2024.app.ui.screens.content.master.views

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.theme.locals.Locals.dimensDp

@Composable
fun FilmMaskImageView(
    model: String?,
    imageSize: Dp
) {

    Box(
        modifier = Modifier
            .wrapContentSize()
            .size(imageSize)
            .sizeIn(
                minWidth = dimensDp.minImageSize,
                minHeight = dimensDp.minImageSize,
                maxWidth = imageSize,
                maxHeight = imageSize
            ),
        contentAlignment = Alignment.Center
    ) {
        if (model.isNullOrEmpty()) {
            CircularProgressIndicator()
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
