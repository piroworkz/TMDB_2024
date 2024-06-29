package com.davidluna.architectcoders2024.media_ui.view.details.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.composables.shimmer

@Composable
fun PostersPagerView(
    images: List<String>
) {
    val duration = remember { mutableIntStateOf(1000) }
    val pagerState =
        rememberPagerState(initialPage = 0, pageCount = { images.size })

    AnimatedContent(
        targetState = images.isNotEmpty(),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3 / 5F, matchHeightConstraintsFirst = true),
        transitionSpec = {
            (
                    fadeIn(
                        animationSpec = tween(duration.intValue)
                    ) + slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(duration.intValue)
                    )
                    )
                .togetherWith(
                    fadeOut(tween(duration.intValue))
                )
        },
        label = "shimmer"
    ) {
        if (it) {
            HorizontalPager(state = pagerState) {
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
                    .shimmer(images.isEmpty()),
            )
        }
    }
}