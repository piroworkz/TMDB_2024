package com.davidluna.architectcoders2024.media_ui.view.details

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.composables.ErrorDialogView
import com.davidluna.architectcoders2024.media_ui.presenter.detail.MovieDetailEvent
import com.davidluna.architectcoders2024.media_ui.presenter.detail.MovieDetailViewModel
import com.davidluna.architectcoders2024.media_ui.view.details.composables.MovieCastView
import com.davidluna.architectcoders2024.media_ui.view.details.composables.MovieDetailsView
import com.davidluna.architectcoders2024.media_ui.view.details.composables.PostersPagerView
import com.davidluna.architectcoders2024.media_ui.view.details.composables.joinImages
import com.davidluna.architectcoders2024.media_ui.view.media.composables.MoviesLazyRow
import com.davidluna.architectcoders2024.navigation.model.MoviesNavigation
import com.davidluna.architectcoders2024.navigation.model.YoutubeNavigation

@Composable
fun MovieDetailScreen(
    state: MovieDetailViewModel.State,
    sendEvent: (MovieDetailEvent) -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        sendEvent(MovieDetailEvent.OnViewReady)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            PostersPagerView(images = joinImages(state))
            MovieDetailsView(state.movieDetail) {
                sendEvent(
                    MovieDetailEvent.OnNavigate(
                        YoutubeNavigation.Video(
                            movieId = state.movieDetail?.id ?: 0
                        )
                    )
                )
            }
            Spacer(modifier = Modifier.padding(all = 16.dp))

            MovieCastView(state.movieCredits)


            MoviesLazyRow(title = R.string.title_recommended_movies, flow = state.recommendations) {
                sendEvent(MovieDetailEvent.OnNavigate(MoviesNavigation.Detail(it)))
            }

            MoviesLazyRow(title = R.string.title_similar_movies, flow = state.similar) {
                sendEvent(MovieDetailEvent.OnNavigate(MoviesNavigation.Detail(it)))
            }

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
    com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(com.davidluna.architectcoders2024.core_ui.composables.appGradient())
        ) {
            MovieDetailScreen(
                state = MovieDetailViewModel.State(),
                sendEvent = {}
            )
        }
    }
}