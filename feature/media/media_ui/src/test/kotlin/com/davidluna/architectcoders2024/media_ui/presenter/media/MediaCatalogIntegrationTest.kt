package com.davidluna.architectcoders2024.media_ui.presenter.media

import androidx.paging.PagingData
import app.cash.turbine.test
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Media
import com.davidluna.architectcoders2024.media_ui.presenter.media.MoviesEvent.OnMovieClicked
import com.davidluna.architectcoders2024.navigation.domain.destination.MediaNavigation.Detail
import com.davidluna.architectcoders2024.test_shared_framework.integration.di.UseCasesModuleDI
import com.davidluna.architectcoders2024.test_shared_framework.remote.fakeMediaItem
import com.davidluna.architectcoders2024.test_shared_framework.rules.CoroutineTestRule
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.onEach
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

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem())
                    .isEqualTo(initialState.copy(contentKind = ContentKind.MOVIE))
                Truth.assertThat(awaitItem().firstList).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().secondList).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().fourthList).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().thirdList).isNotEqualTo(expected)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN (event is OnMovieClicked) WHEN (viewmodel receives event) THEN (should update destination state MediaNavigation Detail )`() =
        runTest {
            val empty = emptyFlow<Flow<PagingData<Media>>>()
            val media = fakeMediaItem
            val viewModel = buildViewModel()

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem())
                    .isEqualTo(initialState.copy(contentKind = ContentKind.MOVIE))
                Truth.assertThat(awaitItem().firstList).isNotEqualTo(empty)
                Truth.assertThat(awaitItem().secondList).isNotEqualTo(empty)
                Truth.assertThat(awaitItem().fourthList).isNotEqualTo(empty)
                Truth.assertThat(awaitItem().thirdList).isNotEqualTo(empty)
                viewModel.sendEvent(OnMovieClicked(Detail(media.id, media.title)))
                Truth.assertThat(awaitItem().destination).isEqualTo(Detail(media.id, media.title))
                cancelAndIgnoreRemainingEvents()
            }
        }


    private fun buildViewModel() = MediaCatalogViewModel(
        UseCasesModuleDI().getMediaCatalogUseCase, UseCasesModuleDI().getContentKindUseCase
    )

}