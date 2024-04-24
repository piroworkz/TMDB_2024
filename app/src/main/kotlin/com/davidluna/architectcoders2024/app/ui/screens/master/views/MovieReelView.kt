package com.davidluna.architectcoders2024.app.ui.screens.master.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie

@Composable
fun MovieReelView(
    title: String,
    movies: List<RemoteMovie> = emptyList(),
    onMovieSelected: (Int) -> Unit
) {
    val imageSize = LocalConfiguration.current.screenWidthDp.dp / 2
    var showMore by remember { mutableStateOf(false) }
    val state = rememberLazyListState()
    Spacer(
        modifier = Modifier.padding(top = 32.dp)
    )
    ReelTitleView(title = title)
    LazyRow(
        modifier = Modifier
            .wrapContentHeight(),
        state = state
    ) {
        items(items = movies) { movie ->
            showMore = movies.last() == movie
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .clickable { onMovieSelected(movie.id) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    FilmMaskImageView(movie.posterPath?.buildModel(), imageSize)
                    MovieTitleView(movie.title, imageSize)
                }
                if (showMore) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterEnd)
                            .background(MaterialTheme.colorScheme.secondary)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ChevronRight,
                            contentDescription = Icons.Outlined.ChevronRight.name,
                            modifier = Modifier.size(imageSize),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }
        }
    }
}



