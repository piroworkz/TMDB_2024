package com.davidluna.architectcoders2024.app.ui.screens.content.master.views

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
import com.davidluna.architectcoders2024.app.ui.theme.locals.Locals
import com.davidluna.architectcoders2024.domain.responses.movies.Content

@Composable
fun ReelView(
    title: String,
    movies: LazyPagingItems<Content>,
    onMovieSelected: (Int) -> Unit
) {
    val imageSize = LocalConfiguration.current.screenWidthDp.dp / 2
    Spacer(
        modifier = Modifier.padding(top = Locals.dimensDp.xLarge)
    )
    if (movies.itemCount != 0) {
        ReelTitleView(title = title)
        LazyRow(
            modifier = Modifier
                .wrapContentHeight(),
        ) {
            items(movies.itemCount) {
                val movie: Content = movies[it] ?: return@items
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .clickable { onMovieSelected(movie.id) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    FilmMaskImageView(
                        model = movie.posterPath,
                        imageSize = imageSize
                    )
                    ItemTitleView(movie.title, imageSize)
                }
            }
        }
    }
}
