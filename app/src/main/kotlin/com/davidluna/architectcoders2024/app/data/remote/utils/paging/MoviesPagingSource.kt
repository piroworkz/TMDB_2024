package com.davidluna.architectcoders2024.app.data.remote.utils.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.davidluna.architectcoders2024.domain.responses.movies.Movie
import com.davidluna.architectcoders2024.domain.responses.movies.Results
import com.davidluna.architectcoders2024.usecases.movies.GetMoviesUseCase

class MoviesPagingSource(
    private val useCase: GetMoviesUseCase,
    private val endpoint: String
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> = try {
        val page = params.key ?: 1
        val response: Results<Movie>? = useCase(endpoint = endpoint, page = page).fold(
            ifLeft = { null },
            ifRight = { it }
        )
        val movies =
            response?.results?.filter { it.posterPath.isNotEmpty() } ?: emptyList()

        val next =
            if (response?.page == response?.totalPages) null else response?.page?.plus(1)
        LoadResult.Page(
            data = movies,
            prevKey = if (page == 1) null else page - 1,
            nextKey = next
        )
    } catch (e: Exception) {
        e.printStackTrace()
        LoadResult.Error(e)
    }
}
