package com.davidluna.architectcoders2024.app.ui.screens.movies.master.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.composables.ErrorDialogView
import com.davidluna.architectcoders2024.app.ui.theme.locals.Locals.dimensDp
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
    val size = LocalConfiguration.current.screenWidthDp.dp / 2

    when (movies.loadState.refresh) {
        is LoadState.Error -> {
            ErrorDialogView(
                error = AppError.Unknown(
                    0,
                    stringResource(R.string.something_went_wrong), false
                )
            ) {
                movies.refresh()
            }
        }

        LoadState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensDp.xLarge)
                    .size(size),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is LoadState.NotLoading -> {
            MovieReelView(
                title = stringResource(title),
                movies = movies,
            ) { onMovieClicked(it) }
            Spacer(modifier = Modifier.padding(top = dimensDp.xLarge))
        }
    }

}
