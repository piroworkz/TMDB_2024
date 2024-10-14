package com.davidluna.tmdb.media_ui.view.details.composables

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State
import coil.compose.AsyncImagePainter.State.Loading
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.composables.shimmer
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag

@Composable
fun PostersPagerView(
    images: List<String>,
) {
    val pagerState =
        rememberPagerState(initialPage = 0, pageCount = { images.size })
    var loadState: State by remember { mutableStateOf(Loading(null)) }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .shimmer(loadState == Loading(null))
            .aspectRatio(3 / 5F, matchHeightConstraintsFirst = true)
            .testTag(MediaTag.POSTERS_PAGER_VIEW)
    ) { currentIndex: Int ->
        val image = images[currentIndex]
        AsyncImage(
            model = image,
            contentDescription = stringResource(R.string.page_image_description),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3 / 5F, matchHeightConstraintsFirst = true)
                .testTag(MediaTag.PAGER_IMAGE),
            contentScale = ContentScale.Crop,
            onState = { loadState = it }
        )
    }
}