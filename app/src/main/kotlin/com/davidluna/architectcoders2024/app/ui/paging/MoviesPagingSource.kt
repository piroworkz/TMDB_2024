package com.davidluna.architectcoders2024.app.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.davidluna.architectcoders2024.domain.responses.movies.Content
import com.davidluna.architectcoders2024.domain.responses.movies.Results
import com.davidluna.architectcoders2024.usecases.movies.GetContentUseCase

class MoviesPagingSource(
    private val useCase: GetContentUseCase,
    private val endpoint: String
) : PagingSource<Int, Content>() {

    override fun getRefreshKey(state: PagingState<Int, Content>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
        return try {
            val page: Int = params.key ?: 1
            val response: Results<Content>? =
                useCase(endpoint, page).fold(
                    ifLeft = { null },
                    ifRight = { it }
                )

            val movies: List<Content> =
                response?.results?.filter { it.posterPath.isNotEmpty() } ?: emptyList()

            val next: Int? =
                if (response?.totalPages == 0 || response?.page == response?.totalPages) {
                    null
                } else {
                    response?.page?.plus(1)
                }
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
}
