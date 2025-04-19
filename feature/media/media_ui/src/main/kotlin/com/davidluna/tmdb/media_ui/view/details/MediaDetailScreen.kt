package com.davidluna.tmdb.media_ui.view.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_ui.composables.ErrorDialogView
import com.davidluna.tmdb.core_ui.composables.appGradient
import com.davidluna.tmdb.core_ui.navigation.destination.YoutubeNavigation.Video
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.media_ui.presenter.detail.MovieDetailEvent
import com.davidluna.tmdb.media_ui.presenter.detail.MovieDetailEvent.OnMovieSelected
import com.davidluna.tmdb.media_ui.presenter.detail.MovieDetailEvent.OnNavigate
import com.davidluna.tmdb.media_ui.presenter.detail.MovieDetailViewModel
import com.davidluna.tmdb.media_ui.view.details.composables.MediaCastView
import com.davidluna.tmdb.media_ui.view.details.composables.MediaDetailsView
import com.davidluna.tmdb.media_ui.view.details.composables.PostersPagerView
import com.davidluna.tmdb.media_ui.view.details.composables.fakeDetails
import com.davidluna.tmdb.media_ui.view.details.composables.joinImages
import com.davidluna.tmdb.media_ui.view.media.composables.ReelView

@Composable
fun MediaDetailScreen(
    state: MovieDetailViewModel.State,
    sendEvent: (MovieDetailEvent) -> Unit,
) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(state.mediaDetails) {
        lazyListState.animateScrollToItem(0)
    }

    Box(contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyListState
        ) {
            item {
                PostersPagerView(images = joinImages(state))
            }

            item {
                MediaDetailsView(state.mediaDetails) {
                    sendEvent(OnNavigate(Video(mediaId = state.mediaDetails?.id ?: 0)))
                }
                Spacer(modifier = Modifier.padding(all = 16.dp))
            }

            item {
                MediaCastView(state.movieCredits)
            }

            item {
                Spacer(modifier = Modifier.padding(top = Dimens.margins.large))
            }
            items(state.catalogs) { catalog ->
                val list = catalog.flow.collectAsLazyPagingItems()
                ReelView(
                    title = catalog.catalogName,
                    list = list,
                ) { id, title ->
                    sendEvent(OnMovieSelected(id, title))
                }
                Spacer(modifier = Modifier.padding(top = Dimens.margins.xLarge))
            }
        }

        ErrorDialogView(error = state.appError as? AppError.Message) {
            sendEvent(MovieDetailEvent.ResetError)
        }
    }

}


@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun MediaDetailScreenPreview() {
    TmdbTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(appGradient())
        ) {
            MediaDetailScreen(
                state = MovieDetailViewModel.State(mediaDetails = fakeDetails),
                sendEvent = {}
            )
        }
    }
}