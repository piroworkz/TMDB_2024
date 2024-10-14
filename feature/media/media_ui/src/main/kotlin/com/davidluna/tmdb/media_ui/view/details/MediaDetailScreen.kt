package com.davidluna.tmdb.media_ui.view.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.composables.ErrorDialogView
import com.davidluna.tmdb.core_ui.composables.appGradient
import com.davidluna.tmdb.core_ui.navigation.destination.YoutubeNavigation.Video
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAILS_RECOMMENDED_LAZY_ROW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAILS_RECOMMENDED_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAILS_SIMILAR_LAZY_ROW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAILS_SIMILAR_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAIL_MAIN_COLUMN
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAIL_SCREEN
import com.davidluna.tmdb.media_ui.presenter.detail.MovieDetailEvent
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
    val scrollState = rememberScrollState()

    LaunchedEffect(state.movieDetail) {
        scrollState.animateScrollTo(0)
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .testTag(MEDIA_DETAIL_SCREEN),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .testTag(MEDIA_DETAIL_MAIN_COLUMN)
        ) {

            PostersPagerView(images = joinImages(state))

            MediaDetailsView(state.movieDetail) {
                sendEvent(OnNavigate(Video(mediaId = state.movieDetail?.id ?: 0)))
            }

            Spacer(modifier = Modifier.padding(all = 16.dp))

            MediaCastView(state.movieCredits)


            ReelView(
                modifier = Modifier.testTag(MEDIA_DETAILS_RECOMMENDED_TITLE_VIEW),
                lazyRowModifier = Modifier.testTag(MEDIA_DETAILS_RECOMMENDED_LAZY_ROW),
                title = stringResource(R.string.title_recommended_movies),
                list = state.recommendations.collectAsLazyPagingItems()
            ) { id, title -> sendEvent(MovieDetailEvent.OnMovieSelected(id, title)) }

            ReelView(
                modifier = Modifier.testTag(MEDIA_DETAILS_SIMILAR_TITLE_VIEW),
                lazyRowModifier = Modifier.testTag(MEDIA_DETAILS_SIMILAR_LAZY_ROW),
                title = stringResource(R.string.title_similar_movies),
                list = state.similar.collectAsLazyPagingItems()
            ) { id, title -> sendEvent(MovieDetailEvent.OnMovieSelected(id, title)) }

            Spacer(modifier = Modifier.padding(all = 16.dp))
        }

        if (state.isLoading) {
            CircularProgressIndicator()
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
private fun MovieDetailScreenPreview() {
    TmdbTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(appGradient())
        ) {
            MediaDetailScreen(
                state = MovieDetailViewModel.State(movieDetail = fakeDetails),
                sendEvent = {}
            )
        }
    }
}