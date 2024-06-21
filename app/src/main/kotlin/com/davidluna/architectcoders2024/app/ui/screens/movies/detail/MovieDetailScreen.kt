package com.davidluna.architectcoders2024.app.ui.screens.movies.detail

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.composables.ErrorDialogView
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MoviesGraph
import com.davidluna.architectcoders2024.app.ui.screens.login.views.appGradient
import com.davidluna.architectcoders2024.app.ui.screens.movies.detail.views.MovieCastView
import com.davidluna.architectcoders2024.app.ui.screens.movies.detail.views.MovieDetailsView
import com.davidluna.architectcoders2024.app.ui.screens.movies.detail.views.PostersPagerView
import com.davidluna.architectcoders2024.app.ui.screens.movies.detail.views.joinImages
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.views.MoviesLazyRow
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme

@Composable
fun MovieDetailScreen(
    state: MovieDetailViewModel.State,
    sendEvent: (MovieDetailEvent) -> Unit
) {

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
                        MoviesGraph.VideoPlayer(
                            movieId = state.movieDetail?.id ?: 0
                        )
                    )
                )
            }
            Spacer(modifier = Modifier.padding(all = 16.dp))

            MovieCastView(state.movieCredits)


            MoviesLazyRow(title = R.string.title_recommended_movies, flow = state.recommendations) {
                sendEvent(MovieDetailEvent.OnNavigate(MoviesGraph.Detail(it)))
            }

            MoviesLazyRow(title = R.string.title_similar_movies, flow = state.similar) {
                sendEvent(MovieDetailEvent.OnNavigate(MoviesGraph.Detail(it)))
            }

            Spacer(modifier = Modifier.padding(all = 16.dp))
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        ErrorDialogView(error = state.appError) {
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
            MovieDetailScreen(
                state = MovieDetailViewModel.State(),
                sendEvent = {}
            )
        }
    }
}