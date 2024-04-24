package com.davidluna.architectcoders2024.app.ui.screens.movies.detail.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovieDetail
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.views.ReelTitleView

@Composable
fun MovieDetailsView(
    movieDetail: RemoteMovieDetail?,
    playTrailer: () -> Unit
) {
    ReelTitleView(title = movieDetail?.title)
    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RectangleShape,
        colors = cardColors()
    ) {
        Spacer(modifier = Modifier.padding(all = 8.dp))
        TextDetailsView(
            movieDetail = movieDetail
        )
        UserScoreView(
            score = movieDetail?.voteAverage?.toFloat() ?: 0F,
            playTrailer = { playTrailer() }
        )
        Spacer(modifier = Modifier.padding(all = 8.dp))
    }
}
