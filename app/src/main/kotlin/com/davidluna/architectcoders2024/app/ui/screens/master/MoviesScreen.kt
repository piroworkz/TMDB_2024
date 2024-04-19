package com.davidluna.architectcoders2024.app.ui.screens.master

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.architectcoders2024.app.ui.screens.master.views.MovieReelView
import com.davidluna.architectcoders2024.app.ui.theme.ArchitectCoders2024Theme

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
                        movies = movies,
                        onMovieSelected = { sendEvent(MoviesEvent.OnMovieClicked(it)) }
                    )
                }
            }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun MoviesScreenPreview() {
    ArchitectCoders2024Theme {
        MoviesScreen(
            state = MoviesViewModel.State()
        ) {

        }
    }
}