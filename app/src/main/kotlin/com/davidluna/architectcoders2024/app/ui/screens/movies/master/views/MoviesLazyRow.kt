package com.davidluna.architectcoders2024.app.ui.screens.movies.master.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.davidluna.architectcoders2024.app.ui.composables.ErrorDialogView
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Movie
import kotlinx.coroutines.flow.Flow

@Composable
fun MoviesLazyRow(
    title: Int,
    flow: Flow<PagingData<Movie>>,
    onMovieClicked: (Int) -> Unit
) {

    val movies: LazyPagingItems<Movie> = flow.collectAsLazyPagingItems()

    when (movies.loadState.refresh) {
        is LoadState.Error -> {
            ErrorDialogView(error = AppError.Unknown(0, "Something went wrong", false)) {
                movies.refresh()
            }
        }

        LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }

        is LoadState.NotLoading -> {
            MovieReelView(
                title = stringResource(title),
                movies = movies
            ) { onMovieClicked(it) }
            Spacer(modifier = Modifier.padding(top = 32.dp))
        }
    }
}
