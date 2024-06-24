package com.davidluna.architectcoders2024.media_ui.presenter.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import arrow.core.Either
import com.davidluna.media_domain.media_domain_entities.Results

class MediaPagingSource<T : Any>(
    private val invoke: suspend (currentPage: Int) -> Either<Throwable, Results<T>>
) : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val page: Int = params.key ?: 1
            val response: Results<T>? = invoke(page)
                .fold(
                    ifLeft = { null },
                    ifRight = { it }
                )
            val data = response?.results ?: emptyList()
            val next = if (response?.totalPages == 0 || response?.page == response?.totalPages) {
                null
            } else {
                response?.page?.plus(1)
            }
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = next
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
