package com.davidluna.architectcoders2024.media_ui.view.media

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.core_ui.theme.dimens.Dimens
import com.davidluna.architectcoders2024.media_ui.presenter.media.MediaCatalogViewModel
import com.davidluna.architectcoders2024.media_ui.presenter.media.MoviesEvent
import com.davidluna.architectcoders2024.media_ui.presenter.media.MoviesEvent.OnMovieClicked
import com.davidluna.architectcoders2024.media_ui.view.media.composables.MediaLazyRow
import com.davidluna.architectcoders2024.navigation.domain.destination.MediaNavigation

@Composable
fun MediaScreen(
    state: MediaCatalogViewModel.State,
    sendEvent: (MoviesEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.padding(top = Dimens.margins.large))
            MediaLazyRow(
                title = R.string.title_popular_movies,
                flow = state.firstList,
                onMovieClicked = { movieId, movieTitle ->
                    sendEvent(OnMovieClicked(MediaNavigation.Detail(movieId, movieTitle)))
                }
            )
        }

        item {
            MediaLazyRow(
                title = R.string.title_top_rated_movies,
                flow = state.secondList,
                onMovieClicked = { movieId, movieTitle ->
                    sendEvent(OnMovieClicked(MediaNavigation.Detail(movieId, movieTitle)))
                }
            )
        }

        item {
            val title =
                if (state.contentKind == ContentKind.MOVIE) R.string.title_upcoming_movies else R.string.title_airing_today
            MediaLazyRow(
                title = title,
                flow = state.thirdList,
                onMovieClicked = { movieId, movieTitle ->
                    sendEvent(OnMovieClicked(MediaNavigation.Detail(movieId, movieTitle)))
                }
            )
        }

        item {
            val title =
                if (state.contentKind == ContentKind.MOVIE) R.string.title_now_playing_movies else R.string.title_on_air
            MediaLazyRow(
                title = title,
                flow = state.fourthList,
                onMovieClicked = { movieId, movieTitle ->
                    sendEvent(OnMovieClicked(MediaNavigation.Detail(movieId, movieTitle)))
                }
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
        MediaScreen(
            state = MediaCatalogViewModel.State()
        ) {

        }
    }
}