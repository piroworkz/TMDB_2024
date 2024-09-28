package com.davidluna.tmdb.media_ui.presenter.media

import androidx.paging.PagingData
import app.cash.turbine.test
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_ui.navigation.destination.MediaNavigation.Detail
import com.davidluna.tmdb.fakes.FakeMediaDI
import com.davidluna.tmdb.fakes.fakeMediaItem
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.media_ui.presenter.media.MediaEvent.OnMovieClicked
import com.davidluna.tmdb.test_shared.rules.CoroutineTestRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MediaCatalogIntegrationTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val initialState = MediaCatalogViewModel.State()

    @Test
    fun `GIVEN (contentKind state = MOVIE) WHEN (viewModel is initialized) THEN (should fetch movies catalogs from service)`() =
        runTest {
            val expected = emptyFlow<Flow<PagingData<Media>>>()

            val viewModel = buildViewModel()

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem())
                    .isEqualTo(initialState.copy(contentKind = com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE))
                viewModel.sendEvent(MediaEvent.OnUiReady(com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE))
                assertThat(awaitItem().firstList).isNotEqualTo(expected)
                assertThat(awaitItem().secondList).isNotEqualTo(expected)
                assertThat(awaitItem().fourthList).isNotEqualTo(expected)
                assertThat(awaitItem().thirdList).isNotEqualTo(expected)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN (event is OnMovieClicked) WHEN (viewmodel receives event) THEN (should update destination state MediaNavigation Detail )`() =
        runTest {
            val empty = emptyFlow<Flow<PagingData<Media>>>()
            val media = fakeMediaItem
            val viewModel = buildViewModel()

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem())
                    .isEqualTo(initialState.copy(contentKind = com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE))
                viewModel.sendEvent(MediaEvent.OnUiReady(com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE))
                assertThat(awaitItem().firstList).isNotEqualTo(empty)
                assertThat(awaitItem().secondList).isNotEqualTo(empty)
                assertThat(awaitItem().fourthList).isNotEqualTo(empty)
                assertThat(awaitItem().thirdList).isNotEqualTo(empty)
                viewModel.sendEvent(OnMovieClicked(Detail(media.id, media.title)))
                assertThat(awaitItem().destination).isEqualTo(Detail(media.id, media.title))
                cancelAndIgnoreRemainingEvents()
            }
        }


    private fun buildViewModel() = MediaCatalogViewModel(
        FakeMediaDI().mediaCatalogUseCase,
        FakeMediaDI().getContentKindUseCase
    )

}