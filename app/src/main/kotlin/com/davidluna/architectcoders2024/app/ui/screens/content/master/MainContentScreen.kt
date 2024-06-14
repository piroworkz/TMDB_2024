package com.davidluna.architectcoders2024.app.ui.screens.content.master

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.ItemsGraph.Detail
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.ContentType.MOVIE
import com.davidluna.architectcoders2024.app.ui.screens.content.master.MainContentEvent.OnMovieClicked
import com.davidluna.architectcoders2024.app.ui.screens.content.master.views.ItemsLazyRow
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.app.ui.theme.locals.Locals.dimensDp

@Composable
fun MainContentScreen(
    state: MainContentViewModel.State,
    sendEvent: (MainContentEvent) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.padding(top = dimensDp.large))
            ItemsLazyRow(
                title = R.string.title_popular_movies,
                flow = state.firstList,
                onMovieClicked = { sendEvent(OnMovieClicked(Detail(it, state.contentType))) }
            )
        }

        item {
            ItemsLazyRow(
                title = R.string.title_top_rated_movies,
                flow = state.secondList
            ) { sendEvent(OnMovieClicked(Detail(it, state.contentType))) }
        }

        item {
            val title =
                if (state.contentType == MOVIE) R.string.title_upcoming_movies else R.string.title_airing_today
            ItemsLazyRow(
                title = title,
                flow = state.thirdList
            ) { sendEvent(OnMovieClicked(Detail(it, state.contentType))) }
        }

        item {
            val title =
                if (state.contentType == MOVIE) R.string.title_now_playing_movies else R.string.title_on_air
            ItemsLazyRow(
                title = title,
                flow = state.fourthList
            ) { sendEvent(OnMovieClicked(Detail(it, state.contentType))) }
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
        MainContentScreen(
            state = MainContentViewModel.State()
        ) {

        }
    }
}