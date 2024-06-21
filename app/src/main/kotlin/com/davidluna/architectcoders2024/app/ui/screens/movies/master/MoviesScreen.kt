package com.davidluna.architectcoders2024.app.ui.screens.movies.master

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MoviesNavigation
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.MoviesEvent.OnMovieClicked
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.views.MoviesLazyRow
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.app.ui.theme.locals.Locals.dimensDp
import com.davidluna.architectcoders2024.domain.ContentKind

@Composable
fun MoviesScreen(
    state: MoviesViewModel.State,
    sendEvent: (MoviesEvent) -> Unit
) {

    LaunchedEffect(key1 = state.contentKind) {

    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.padding(top = dimensDp.large))
            MoviesLazyRow(
                title = R.string.title_popular_movies,
                flow = state.firstList,
                onMovieClicked = { sendEvent(OnMovieClicked(MoviesNavigation.Detail(it))) }
            )
        }

        item {
            MoviesLazyRow(
                title = R.string.title_top_rated_movies,
                flow = state.secondList,
                onMovieClicked = { sendEvent(OnMovieClicked(MoviesNavigation.Detail(it))) }
            )
        }

        item {
            val title =
                if (state.contentKind == ContentKind.MOVIE) R.string.title_upcoming_movies else R.string.title_airing_today
            MoviesLazyRow(
                title = title,
                flow = state.thirdList,
                onMovieClicked = { sendEvent(OnMovieClicked(MoviesNavigation.Detail(it))) }
            )
        }

        item {
            val title =
                if (state.contentKind == ContentKind.MOVIE) R.string.title_now_playing_movies else R.string.title_on_air
            MoviesLazyRow(
                title = title,
                flow = state.fourthList,
                onMovieClicked = { sendEvent(OnMovieClicked(MoviesNavigation.Detail(it))) }
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