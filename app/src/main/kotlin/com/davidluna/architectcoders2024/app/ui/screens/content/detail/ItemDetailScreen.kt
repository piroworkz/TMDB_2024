package com.davidluna.architectcoders2024.app.ui.screens.content.detail

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
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.composables.ErrorDialogView
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.ItemsGraph
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.ItemsGraph.VideoPlayer
import com.davidluna.architectcoders2024.app.ui.screens.content.detail.ItemDetailEvent.OnNavigate
import com.davidluna.architectcoders2024.app.ui.screens.content.detail.views.ItemCastView
import com.davidluna.architectcoders2024.app.ui.screens.content.detail.views.ItemDetailsView
import com.davidluna.architectcoders2024.app.ui.screens.content.detail.views.PostersPagerView
import com.davidluna.architectcoders2024.app.ui.screens.content.detail.views.joinImages
import com.davidluna.architectcoders2024.app.ui.screens.content.master.views.ItemsLazyRow
import com.davidluna.architectcoders2024.app.ui.screens.login.views.appGradient
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.app.ui.theme.locals.Locals.dimensDp

@Composable
fun ItemDetailScreen(
    state: ItemDetailViewModel.State,
    sendEvent: (ItemDetailEvent) -> Unit
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
            ItemDetailsView(state.movieDetail) {
                sendEvent(OnNavigate(VideoPlayer(movieId = state.movieDetail?.id)))
            }
            Spacer(modifier = Modifier.padding(all = dimensDp.large))

            ItemCastView(state.movieCredits)


            ItemsLazyRow(
                title = R.string.title_recommended_movies,
                flow = state.recommendations
            ) {
                sendEvent(OnNavigate(ItemsGraph.Detail(it, state.contentType)))
            }

            ItemsLazyRow(
                title = R.string.title_similar_movies,
                flow = state.similar
            ) {
                sendEvent(OnNavigate(ItemsGraph.Detail(it, state.contentType)))
            }

            Spacer(modifier = Modifier.padding(all = dimensDp.large))
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        ErrorDialogView(error = state.appError) {
            sendEvent(ItemDetailEvent.ResetError)
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
            ItemDetailScreen(
                state = ItemDetailViewModel.State(),
                sendEvent = {}
            )
        }
    }
}