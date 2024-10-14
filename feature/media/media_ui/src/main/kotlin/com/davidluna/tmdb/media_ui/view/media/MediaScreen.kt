package com.davidluna.tmdb.media_ui.view.media

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.navigation.destination.MediaNavigation.Detail
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_CATALOG_SCREEN_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_FIRST_LIST_LAZY_ROW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_FIRST_LIST_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_FOURTH_LIST_LAZY_ROW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_FOURTH_LIST_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_SECOND_LIST_LAZY_ROW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_SECOND_LIST_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_THIRD_LIST_LAZY_ROW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_THIRD_LIST_TITLE_VIEW
import com.davidluna.tmdb.media_ui.presenter.media.MediaCatalogViewModel
import com.davidluna.tmdb.media_ui.presenter.media.MediaEvent
import com.davidluna.tmdb.media_ui.presenter.media.MediaEvent.OnMovieClicked
import com.davidluna.tmdb.media_ui.view.media.composables.ReelView

@Composable
fun MediaCatalogScreen(
    state: MediaCatalogViewModel.State,
    sendEvent: (MediaEvent) -> Unit
) {
    val firstList = state.firstList.collectAsLazyPagingItems()
    val secondList = state.secondList.collectAsLazyPagingItems()
    val thirdList = state.thirdList.collectAsLazyPagingItems()
    val fourthList = state.fourthList.collectAsLazyPagingItems()

    LaunchedEffect(state.contentKind) {
        if (state.contentKind != ContentKind.UNDEFINED) {
            sendEvent(MediaEvent.OnUiReady(state.contentKind))
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag(MEDIA_CATALOG_SCREEN_VIEW)
    ) {
        item {
            Spacer(modifier = Modifier.padding(top = Dimens.margins.large))
            ReelView(
                modifier = Modifier.testTag(MEDIA_FIRST_LIST_TITLE_VIEW),
                lazyRowModifier = Modifier.testTag(MEDIA_FIRST_LIST_LAZY_ROW),
                title = stringResource(R.string.title_popular_movies),
                list = firstList,
            ) { id, title ->
                sendEvent(OnMovieClicked(Detail(id, title)))
            }

            Spacer(modifier = Modifier.padding(top = Dimens.margins.xLarge))
        }

        item {
            ReelView(
                modifier = Modifier.testTag(MEDIA_SECOND_LIST_TITLE_VIEW),
                lazyRowModifier = Modifier.testTag(MEDIA_SECOND_LIST_LAZY_ROW),
                title = stringResource(R.string.title_top_rated_movies),
                list = secondList,
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
                modifier = Modifier.testTag(MEDIA_THIRD_LIST_TITLE_VIEW),
                lazyRowModifier = Modifier.testTag(MEDIA_THIRD_LIST_LAZY_ROW),
                title = stringResource(resourceId),
                list = thirdList,
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
                modifier = Modifier.testTag(MEDIA_FOURTH_LIST_TITLE_VIEW),
                lazyRowModifier = Modifier.testTag(MEDIA_FOURTH_LIST_LAZY_ROW),
                title = stringResource(resourceId),
                list = fourthList,
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