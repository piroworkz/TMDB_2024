package com.davidluna.architectcoders2024.media_ui.view.media.composables

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
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.davidluna.architectcoders2024.core_ui.theme.dimens.Dimens
import com.davidluna.media_domain.media_domain_entities.Media

@Composable
fun ReelView(
    title: String,
    movies: LazyPagingItems<Media>,
    onMovieSelected: (Int) -> Unit
) {
    val imageSize = LocalConfiguration.current.screenWidthDp.dp / 2
    Spacer(
        modifier = Modifier.padding(top = Dimens.margins.xLarge)
    )
    if (movies.itemCount != 0) {
        ReelTitleView(title = title)
        LazyRow(
            modifier = Modifier
                .wrapContentHeight(),
        ) {
            items(movies.itemCount,
                key = { movies[it]?.id ?: it }) {
                val movie: Media? = movies[it]
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .clickable { movie?.id?.let { movieId -> onMovieSelected(movieId) } },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    FilmMaskImageView(
                        model = movie?.posterPath,
                        imageSize = imageSize
                    )
                    MediaTitleView(movie?.title, imageSize)
                }
            }
        }
    }
}
