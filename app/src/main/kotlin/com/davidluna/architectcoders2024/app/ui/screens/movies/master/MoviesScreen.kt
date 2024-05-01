package com.davidluna.architectcoders2024.app.ui.screens.movies.master

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MoviesGraph.Detail
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.MoviesEvent.OnMovieClicked
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.views.MoviesLazyRow
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.app.ui.theme.locals.Locals.dimensDp

@Composable
fun MoviesScreen(
    state: MoviesViewModel.State,
    sendEvent: (event: MoviesEvent) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        item {
            Spacer(modifier = Modifier.padding(top = dimensDp.large))
            MoviesLazyRow(
                title = R.string.title_popular_movies,
                flow = state.popularMovies,
                onMovieClicked = { sendEvent(OnMovieClicked(Detail(it))) }
            )
        }

        item {
            MoviesLazyRow(
                title = R.string.title_now_playing_movies,
                flow = state.nowPlayingMovies
            ) { sendEvent(OnMovieClicked(Detail(it))) }
        }

        item {
            MoviesLazyRow(
                title = R.string.title_top_rated_movies,
                flow = state.topRatedMovies
            ) { sendEvent(OnMovieClicked(Detail(it))) }
        }

        item {
            MoviesLazyRow(
                title = R.string.title_upcoming_movies,
                flow = state.upcomingMovies
            ) { sendEvent(OnMovieClicked(Detail(it))) }
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