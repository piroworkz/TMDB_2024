package com.davidluna.architectcoders2024.app.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import arrow.core.Either
import com.davidluna.architectcoders2024.app.data.remote.model.RemoteError
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteResults
import com.davidluna.architectcoders2024.app.utils.log

class MoviesPagingSource(
    private val request: suspend (page: Int) -> Either<RemoteError, RemoteResults<RemoteMovie>>,
) : PagingSource<Int, RemoteMovie>() {

    override fun getRefreshKey(state: PagingState<Int, RemoteMovie>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RemoteMovie> = try {
        val page = params.key ?: 1
        val results = request.invoke(page)
        results.onRight {
            it.page.toString().log("getNowPlayingMovies current page")
        }
        val movies = results.fold(
            ifLeft = { emptyList() },
            ifRight = { it.results }
        )
        val next = results.fold(
            ifLeft = { page },
            ifRight = { if (it.page == it.totalPages) null else it.page + 1 }
        )

        LoadResult.Page(
            data = movies,
            prevKey = if (page == 1) null else page - 1,
            nextKey = next
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

}
