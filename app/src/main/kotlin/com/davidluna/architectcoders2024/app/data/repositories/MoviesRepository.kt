package com.davidluna.architectcoders2024.app.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie
import com.davidluna.architectcoders2024.app.data.remote.paging.MoviesPagingSource
import com.davidluna.architectcoders2024.app.data.remote.services.movies.MoviesService
import kotlinx.coroutines.flow.Flow

class MoviesRepository(private val service: MoviesService) {

    fun getMovies(from: String): Flow<PagingData<RemoteMovie>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { MoviesPagingSource { service.getMovies(from, it) } }
    ).flow
}
