package com.davidluna.architectcoders2024.media_ui.view.media

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import com.davidluna.architectcoders2024.core_domain.entities.ContentKind
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.core_ui.theme.dimens.Dimens
import com.davidluna.architectcoders2024.media_domain.entities.tags.MediaTag.MEDIA_CATALOG_SCREEN_VIEW
import com.davidluna.architectcoders2024.media_ui.presenter.media.MediaCatalogViewModel
import com.davidluna.architectcoders2024.media_ui.presenter.media.MoviesEvent
import com.davidluna.architectcoders2024.media_ui.presenter.media.MoviesEvent.OnMovieClicked
import com.davidluna.architectcoders2024.media_ui.view.media.composables.ReelView
import com.davidluna.architectcoders2024.core_ui.navigation.destination.MediaNavigation.Detail

@Composable
fun MediaCatalogScreen(
    state: MediaCatalogViewModel.State,
    sendEvent: (MoviesEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag(MEDIA_CATALOG_SCREEN_VIEW)
    ) {
        item {
            Spacer(modifier = Modifier.padding(top = Dimens.margins.large))
            ReelView(
                title = stringResource(R.string.title_popular_movies),
                list = state.firstList.collectAsLazyPagingItems(),
            ) { id, title ->
                sendEvent(OnMovieClicked(Detail(id, title)))
            }

            Spacer(modifier = Modifier.padding(top = Dimens.margins.xLarge))
        }

        item {
            ReelView(
                title = stringResource(R.string.title_top_rated_movies),
                list = state.secondList.collectAsLazyPagingItems(),
                onMovieSelected = { id, title ->
                    sendEvent(OnMovieClicked(Detail(id, title)))
                }
            )
            Spacer(modifier = Modifier.padding(top = Dimens.margins.xLarge))
        }

        item {
            val resourceId =
                if (state.contentKind == ContentKind.MOVIE) R.string.title_upcoming_movies else R.string.title_airing_today
            ReelView(
                title = stringResource(resourceId),
                list = state.thirdList.collectAsLazyPagingItems(),
                onMovieSelected = { id, title ->
                    sendEvent(OnMovieClicked(Detail(id, title)))
                }
            )
            Spacer(modifier = Modifier.padding(top = Dimens.margins.xLarge))
        }

        item {
            val resourceId =
                if (state.contentKind == ContentKind.MOVIE) R.string.title_now_playing_movies else R.string.title_on_air
            ReelView(
                title = stringResource(resourceId),
                list = state.fourthList.collectAsLazyPagingItems(),
                onMovieSelected = { id, title ->
                    sendEvent(OnMovieClicked(Detail(id, title)))
                }
            )
            Spacer(modifier = Modifier.padding(top = Dimens.margins.xLarge))
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
        MediaCatalogScreen(
            state = MediaCatalogViewModel.State()
        ) {

        }
    }
}