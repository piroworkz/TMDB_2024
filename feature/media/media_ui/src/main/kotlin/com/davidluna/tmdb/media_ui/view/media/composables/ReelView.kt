package com.davidluna.tmdb.media_ui.view.media.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.REEL_IMAGE_COLUMN_CONTAINER

@Composable
fun ReelView(
    modifier: Modifier = Modifier,
    lazyRowModifier: Modifier = Modifier,
    title: String,
    list: LazyPagingItems<Media>,
    onMovieSelected: (Int, String) -> Unit,
) {
    val imageSize = LocalConfiguration.current.screenWidthDp.dp / 2

    Spacer(
        modifier = Modifier.padding(top = Dimens.margins.xLarge)
    )
    ReelTitleView(modifier = modifier, title = title)
    LazyRow(
        modifier = lazyRowModifier
            .wrapContentHeight(),
    ) {
        when (list.loadState.refresh) {
            is LoadState.Error -> {
                items(5) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        ErrorImageView(imageSize)
                        MediaTitleView("Error...", imageSize)
                    }
                }
            }

            LoadState.Loading -> {
                items(5) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        LoadingImageView(imageSize)
                        MediaTitleView("Loading...", imageSize)
                    }
                }
            }

            is LoadState.NotLoading -> {
                items(list.itemCount) {
                    val media: Media? = list[it]
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                            .clickable {
                                media?.id?.let { id ->
                                    onMovieSelected(id, media.title)
                                }
                            }
                            .testTag(REEL_IMAGE_COLUMN_CONTAINER),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        FilmMaskImageView(
                            model = media?.posterPath,
                            imageSize = imageSize
                        )
                        MediaTitleView(media?.title, imageSize)
                    }
                }
            }
        }
    }
}
