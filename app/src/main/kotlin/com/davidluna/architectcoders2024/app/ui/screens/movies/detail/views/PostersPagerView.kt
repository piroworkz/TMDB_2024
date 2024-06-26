package com.davidluna.architectcoders2024.app.ui.screens.movies.detail.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.davidluna.architectcoders2024.R

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun PostersPagerView(
    images: List<String>
) {
    val pagerState =
        rememberPagerState(initialPage = 0, pageCount = { images.size })

    HorizontalPager(state = pagerState) {
        val image = images[it]
        AsyncImage(
            model = image,
            contentDescription = "Page Image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
    if (images.isEmpty()) {
        Image(
            painter = painterResource(id = R.drawable.demo_thumb),
            contentDescription = "Placeholder",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
    }
}