package com.davidluna.tmdb.media_ui.view.details.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.davidluna.tmdb.core_domain.entities.labels.Animation
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.composables.SlideFromTopAnimation
import com.davidluna.tmdb.core_ui.composables.shimmer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostersPagerView(
    images: List<String>,
) {
    val pagerState =
        rememberPagerState(initialPage = 0, pageCount = { images.size })

    SlideFromTopAnimation(
        target = images.isNotEmpty(),
        label = Animation.SHIMMER
    ) { listIsNotEmpty ->
        if (listIsNotEmpty) {
            HorizontalPager(
                state = pagerState,
            ) {
                val image = images[it]
                AsyncImage(
                    model = image,
                    contentDescription = stringResource(R.string.page_image_description),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3 / 5F, matchHeightConstraintsFirst = true),
                    contentScale = ContentScale.Crop
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3 / 5F, matchHeightConstraintsFirst = true)
                    .shimmer(images.isEmpty())
            )
        }
    }
}