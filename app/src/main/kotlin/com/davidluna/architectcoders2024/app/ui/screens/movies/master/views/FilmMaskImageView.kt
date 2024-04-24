package com.davidluna.architectcoders2024.app.ui.screens.movies.master.views

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidluna.architectcoders2024.R

@Composable
fun FilmMaskImageView(
    model: String? = "",
    imageSize: Dp
) {

    Box(
        modifier = Modifier
            .wrapContentSize()
            .size(imageSize)
            .sizeIn(
                minWidth = 150.dp,
                minHeight = 150.dp,
                maxWidth = imageSize,
                maxHeight = imageSize
            ),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = model,
            contentDescription = "film reel",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )
        Image(
            painter = painterResource(id = R.drawable.film_mask),
            contentDescription = "film reel",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}