package com.davidluna.tmdb.media_ui.presenter.videos

import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import com.davidluna.tmdb.media_domain.entities.Catalog
import com.davidluna.tmdb.media_ui.view.utils.UiState
import com.davidluna.tmdb.media_domain.usecases.GetSelectedMediaCatalog
import com.davidluna.tmdb.media_domain.usecases.GetMediaVideosUseCase
import com.davidluna.tmdb.media_ui.fakeAppError
import com.davidluna.tmdb.media_ui.fakeVideos
import com.davidluna.tmdb.test_shared.rules.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class VideoPlayerViewModelTest {

    @get:Rule(order = 1)
    val mockkRule = MockKRule(this)

    @get:Rule(order = 2)
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var getSelectedMediaCatalogUseCase: GetSelectedMediaCatalog

    @MockK
    private lateinit var getMediaVideosUseCase: GetMediaVideosUseCase

    @Test
    fun `GIVEN initial state WHEN viewModel is created THEN mediaVideos is Loading`() {
        every { getSelectedMediaCatalogUseCase.selectedCatalog } returns emptyFlow()
        val sut = buildSUT()

        assert(sut.mediaVideos.value is UiState.Loading)
    }

    @Test
    fun `GIVEN getSelectedMediaCatalogUseCase emits any value and getMediaVideosUseCase returns Right WHEN fetching videos THEN mediaVideos emits Success with movie videos`() =
        coroutineTestRule.scope.runTest {
            val expected = UiState.Success(fakeVideos)
            every { getSelectedMediaCatalogUseCase.selectedCatalog } returns flowOf(Catalog.MOVIE_UPCOMING)
            coEvery { getMediaVideosUseCase(any(), any()) } returns fakeVideos.right()
            val sut = buildSUT()

            sut.mediaVideos.test {
                skipItems(1)
                val actual = awaitItem()

                assertEquals(expected, actual)
                cancelAndConsumeRemainingEvents()
            }

        }

    @Test
    fun `GIVEN getSelectedMediaCatalogUseCase emits any value and getMediaVideosUseCase returns Left WHEN fetching videos THEN mediaVideos emits Failure`() =
        coroutineTestRule.scope.runTest {
            val expected = UiState.Failure(fakeAppError)
            every { getSelectedMediaCatalogUseCase.selectedCatalog } returns flowOf(Catalog.MOVIE_UPCOMING)
            coEvery { getMediaVideosUseCase(any(), any()) } returns fakeAppError.left()
            val sut = buildSUT()

            sut.mediaVideos.test {
                skipItems(1)
                val actual = awaitItem()

                assertEquals(expected, actual)
                cancelAndConsumeRemainingEvents()
            }

        }

    private fun buildSUT() = VideoPlayerViewModel(
        getSelectedMediaCatalogUseCase = getSelectedMediaCatalogUseCase,
        getMediaVideosUseCase = getMediaVideosUseCase,
        mediaId = 1
    )

}