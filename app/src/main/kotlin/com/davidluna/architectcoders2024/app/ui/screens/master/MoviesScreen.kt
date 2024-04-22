package com.davidluna.architectcoders2024.app.ui.screens.master

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.architectcoders2024.app.ui.screens.master.views.MovieReelView
import com.davidluna.architectcoders2024.app.ui.screens.master.views.buildReelTitle
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme

@Composable
fun MoviesScreen(
    state: MoviesViewModel.State,
    sendEvent: (event: MoviesEvent) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        state.movies
            .groupBy { it.type }
            .values
            .toList()
            .sortedBy { it.first().type }
            .forEachIndexed { _, movies ->
                item {
                    MovieReelView(
                        title = buildReelTitle(movies),
                        movies = movies,
                        onMovieSelected = { sendEvent(MoviesEvent.OnMovieClicked(it)) }
                    )
                }
            }
        item {
            Spacer(
                modifier = Modifier.padding(top = 32.dp)
            )
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun MoviesScreenPreview() {
    TmdbTheme {
        MoviesScreen(
            state = MoviesViewModel.State()
        ) {

        }
    }
}