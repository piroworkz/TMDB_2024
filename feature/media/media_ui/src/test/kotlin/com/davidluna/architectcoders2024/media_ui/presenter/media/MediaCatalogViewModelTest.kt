package com.davidluna.architectcoders2024.media_ui.presenter.media

import androidx.paging.PagingData
import app.cash.turbine.test
import com.davidluna.architectcoders2024.core_domain.entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.GetContentKindUseCase
import com.davidluna.architectcoders2024.media_domain.entities.Media
import com.davidluna.architectcoders2024.media_domain.usecases.GetMediaCatalogUseCase
import com.davidluna.architectcoders2024.test_shared.fakes.fakeNotFoundAppError
import com.davidluna.architectcoders2024.test_shared.rules.CoroutineTestRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class MediaCatalogViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var getContent: GetMediaCatalogUseCase

    @Mock
    private lateinit var getContentKind: GetContentKindUseCase

    private val initialState = MediaCatalogViewModel.State()

    @Test
    fun `GIVEN (viewmodel initialization) WHEN (getContentKindUseCase collection succeeds) THEN (should update contentKind state = ContentKind)`() =
        runTest {

            whenever(getContentKind()).thenReturn(flowOf(ContentKind.MOVIE))

            val viewModel = buildViewModel()

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem().contentKind).isEqualTo(ContentKind.MOVIE)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN (viewModel initialization) WHEN (getContentKindUseCase collection fails) THEN (should update appError state = AppError)`() =
        runTest {
            whenever(getContentKind()).thenReturn(flow { throw fakeNotFoundAppError })

            val viewModel = buildViewModel()

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem().appError).isEqualTo(fakeNotFoundAppError)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN (contentKind is different to UNDEFINED) WHEN (getContentUseCase succeeds) THEN (should return Flow of PagingData Media)`() =
        runTest {
            val expected = emptyFlow<Flow<PagingData<Media>>>()
            whenever(getContentKind()).thenReturn(flowOf(ContentKind.MOVIE))

            val viewModel = buildViewModel()

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem().contentKind).isEqualTo(ContentKind.MOVIE)
                //  PagingData is not a data class, so any equality check would fail. Need to find a way to compare the contents of the PagingData object. Meanwhile, we can check if is not empty.
                assertThat(awaitItem().firstList).isNotEqualTo(expected)
                assertThat(awaitItem().secondList).isNotEqualTo(expected)
                assertThat(awaitItem().fourthList).isNotEqualTo(expected)
                assertThat(awaitItem().thirdList).isNotEqualTo(expected)
                cancelAndIgnoreRemainingEvents()
            }
        }

    private fun buildViewModel() = MediaCatalogViewModel(getContent, getContentKind)

}