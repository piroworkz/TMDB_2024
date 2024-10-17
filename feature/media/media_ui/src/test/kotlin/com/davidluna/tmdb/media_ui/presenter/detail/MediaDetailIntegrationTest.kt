package com.davidluna.tmdb.media_ui.presenter.detail

import app.cash.turbine.test
import com.davidluna.tmdb.fakes.FakeMediaDI
import com.davidluna.tmdb.fakes.empty
import com.davidluna.tmdb.test_shared.rules.CoroutineTestRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MediaDetailIntegrationTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val initialState = MovieDetailViewModel.State()

    @Test
    fun `GIVEN (content kind is not null) WHEN (viewModel init) THEN (should fetch data from service)`() =
        runTest {
            val viewModel = buildViewModel()

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem().contentKind).isNotNull()
                assertThat(awaitItem().recommendations).isNotEqualTo(empty)
                assertThat(awaitItem().similar).isNotEqualTo(empty)
                assertThat(awaitItem().isLoading).isTrue()
                assertThat(awaitItem().movieDetail).isNotNull()
                assertThat(awaitItem().isLoading).isFalse()
                assertThat(awaitItem().isLoading).isTrue()
                assertThat(awaitItem().images).isNotNull()
                assertThat(awaitItem().isLoading).isFalse()
                assertThat(awaitItem().isLoading).isTrue()
                assertThat(awaitItem().movieCredits).isNotNull()
                assertThat(awaitItem().isLoading).isFalse()
                cancelAndIgnoreRemainingEvents()
            }

        }

    private fun buildViewModel() =
        MovieDetailViewModel(
            movieId = 1022789,
            getMovieDetails = FakeMediaDI().getMediaDetailsUseCase,
            getMediaImagesUseCase = FakeMediaDI().getMediaImagesUseCase,
            getMediaCastUseCase = FakeMediaDI().getMediaCastUseCase,
            getContent = FakeMediaDI().getMediaCatalogUseCase,
            getContentKindUseCase = FakeMediaDI().getContentKindUseCase
        )

}