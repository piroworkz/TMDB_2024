package com.davidluna.tmdb.media_ui.view.media.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.media_domain.entities.Media

@Composable
fun ReelView(
    title: String,
    list: LazyPagingItems<Media>,
    onMovieSelected: (Int, String) -> Unit,
) {
    val itemsPerScreenWidth: Int = remember { 2 }
    val imageSize = LocalConfiguration.current.screenWidthDp.dp / itemsPerScreenWidth
    Spacer(modifier = Modifier.padding(top = Dimens.margins.xLarge))
    ReelTitleView(modifier = Modifier, title = title)

    LazyRow(
        modifier = Modifier
            .background(Color.Black)
            .wrapContentHeight(),
    ) {
        if (list.loadState.refresh == LoadState.Loading) {
            items(itemsPerScreenWidth) {
                Column {
                    FilmMaskImageView(
                        model = null,
                        imageSize = imageSize
                    )
                    MediaTitleView("  ", imageSize)
                }
            }
        } else {
            items(list.itemCount) {
                val media: Media? = list[it]
                Column(
                    modifier = Modifier
                        .clickable { media?.id?.let { id -> onMovieSelected(id, media.title) } },
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
