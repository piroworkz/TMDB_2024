package com.davidluna.architectcoders2024.media_ui.presenter.detail

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.core_domain.core_entities.labels.NavArgument
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.GetContentKindUseCase
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Media
import com.davidluna.architectcoders2024.media_domain.media_domain_usecases.GetMediaCastUseCase
import com.davidluna.architectcoders2024.media_domain.media_domain_usecases.GetMediaCatalogUseCase
import com.davidluna.architectcoders2024.media_domain.media_domain_usecases.GetMediaDetailsUseCase
import com.davidluna.architectcoders2024.media_domain.media_domain_usecases.GetMediaImagesUseCase
import com.davidluna.architectcoders2024.media_ui.view.details.composables.fakeDetails
import com.davidluna.architectcoders2024.navigation.domain.destination.MediaNavigation
import com.davidluna.architectcoders2024.navigation.domain.destination.YoutubeNavigation
import com.davidluna.architectcoders2024.test_shared.domain.fakeCastList
import com.davidluna.architectcoders2024.test_shared.domain.fakeContentKind
import com.davidluna.architectcoders2024.test_shared.domain.fakeImages
import com.davidluna.architectcoders2024.test_shared.domain.fakeMediaDetail
import com.davidluna.architectcoders2024.test_shared.domain.fakeNotFoundAppError
import com.davidluna.architectcoders2024.test_shared_framework.rules.CoroutineTestRule
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var getMovieDetails: GetMediaDetailsUseCase

    @Mock
    private lateinit var getMediaImagesUseCase: GetMediaImagesUseCase

    @Mock
    private lateinit var getMediaCastUseCase: GetMediaCastUseCase

    @Mock
    private lateinit var getContent: GetMediaCatalogUseCase

    @Mock
    private lateinit var getContentKindUseCase: GetContentKindUseCase

    private val initialState = MovieDetailViewModel.State()

    @Test
    fun `GIVEN (viewModel is initialized) WHEN (getContentKindUseCase succeeds) THEN (should update contentKind state = ContentKind)`() =
        runTest {

            whenever(getContentKindUseCase.invoke()).thenReturn(flowOf(fakeContentKind))

            val viewModel = buildViewModel()

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().contentKind).isEqualTo(fakeContentKind)
                cancelAndIgnoreRemainingEvents()
            }
        }


    @Test
    fun `GIVEN (viewModel is initialized) WHEN (getContentKindUseCase fails) THEN (should update appError state = AppError)`() =
        runTest {
            whenever(getContentKindUseCase.invoke()).thenReturn(flow { throw fakeNotFoundAppError })

            val viewModel = buildViewModel()

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().appError).isEqualTo(fakeNotFoundAppError)
                cancelAndIgnoreRemainingEvents()
            }

        }

    @Test
    fun `GIVEN (getContentKindUseCase collect succeeds) WHEN (getMovieDetails, getMovieImagesUseCase, getMovieCastUseCase succeed) THEN (should update respective state fields with results)`() =
        runTest {
            val expected = emptyFlow<Flow<PagingData<Media>>>()
            whenever(getContentKindUseCase.invoke()).thenReturn(flowOf(fakeContentKind))
            whenever(getMovieDetails(any())).thenReturn(fakeMediaDetail.right())
            whenever(getMediaImagesUseCase(any())).thenReturn(fakeImages.right())
            whenever(getMediaCastUseCase(any())).thenReturn(fakeCastList.right())

            val viewModel = buildViewModel()

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().contentKind).isEqualTo(fakeContentKind)
                Truth.assertThat(awaitItem().recommendations).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().similar).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().movieDetail).isEqualTo(fakeMediaDetail)
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().images).isEqualTo(fakeImages.map { it.filePath })
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().movieCredits).isEqualTo(fakeCastList)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN (getContentKindUseCase collect succeeds) WHEN (getMovieDetails fails) THEN (should update appError = AppError)`() =
        runTest {
            val expected = emptyFlow<Flow<PagingData<Media>>>()
            whenever(getContentKindUseCase.invoke()).thenReturn(flowOf(fakeContentKind))
            whenever(getMovieDetails(any())).thenReturn(fakeNotFoundAppError.left())
            whenever(getMediaImagesUseCase(any())).thenReturn(fakeImages.right())
            whenever(getMediaCastUseCase(any())).thenReturn(fakeCastList.right())

            val viewModel = buildViewModel()

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().contentKind).isEqualTo(fakeContentKind)
                Truth.assertThat(awaitItem().recommendations).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().similar).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().appError).isEqualTo(fakeNotFoundAppError)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN (getContentKindUseCase collect succeeds) WHEN (getMovieImagesUseCase fails) THEN (should update appError = AppError)`() =
        runTest {
            val expected = emptyFlow<Flow<PagingData<Media>>>()
            whenever(getContentKindUseCase.invoke()).thenReturn(flowOf(fakeContentKind))
            whenever(getMovieDetails(any())).thenReturn(fakeMediaDetail.right())
            whenever(getMediaImagesUseCase(any())).thenReturn(fakeNotFoundAppError.left())
            whenever(getMediaCastUseCase(any())).thenReturn(fakeCastList.right())

            val viewModel = buildViewModel()

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().contentKind).isEqualTo(fakeContentKind)
                Truth.assertThat(awaitItem().recommendations).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().similar).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().movieDetail).isEqualTo(fakeMediaDetail)
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().appError).isEqualTo(fakeNotFoundAppError)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN (getContentKindUseCase collect succeeds) WHEN (getMovieCastUseCase fails) THEN (should update appError = AppError)`() =
        runTest {
            val expected = emptyFlow<Flow<PagingData<Media>>>()
            whenever(getContentKindUseCase.invoke()).thenReturn(flowOf(fakeContentKind))
            whenever(getMovieDetails(any())).thenReturn(fakeMediaDetail.right())
            whenever(getMediaImagesUseCase(any())).thenReturn(fakeImages.right())
            whenever(getMediaCastUseCase(any())).thenReturn(fakeNotFoundAppError.left())

            val viewModel = buildViewModel()

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().contentKind).isEqualTo(fakeContentKind)
                Truth.assertThat(awaitItem().recommendations).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().similar).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().movieDetail).isEqualTo(fakeMediaDetail)
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().images).isEqualTo(fakeImages.map { it.filePath })
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().appError).isEqualTo(fakeNotFoundAppError)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN (user selects a similar or recommended movie) WHEN (event is OnMovieSelected) THEN (should update destination state = MediaNavigation Detail)`() =
        runTest {
            val expected = emptyFlow<Flow<PagingData<Media>>>()
            whenever(getContentKindUseCase.invoke()).thenReturn(flowOf(fakeContentKind))
            whenever(getMovieDetails(any())).thenReturn(fakeMediaDetail.right())
            whenever(getMediaImagesUseCase(any())).thenReturn(fakeImages.right())
            whenever(getMediaCastUseCase(any())).thenReturn(fakeCastList.right())

            val viewModel = buildViewModel()

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().contentKind).isEqualTo(fakeContentKind)
                Truth.assertThat(awaitItem().recommendations).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().similar).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().movieDetail).isEqualTo(fakeMediaDetail)
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().images).isEqualTo(fakeImages.map { it.filePath })
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().movieCredits).isEqualTo(fakeCastList)
                Truth.assertThat(awaitItem().isLoading).isFalse()
                viewModel.sendEvent(
                    MovieDetailEvent.OnMovieSelected(
                        fakeDetails.id,
                        fakeDetails.title
                    )
                )
                Truth.assertThat(awaitItem().destination)
                    .isEqualTo(MediaNavigation.Detail(fakeDetails.id, fakeDetails.title))
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN (user clicks Play Trailer Button) WHEN (event is OnNavigate) THEN (should update destination state = YoutubeNavigation Video)`() =
        runTest {
            val expected = emptyFlow<Flow<PagingData<Media>>>()
            whenever(getContentKindUseCase.invoke()).thenReturn(flowOf(fakeContentKind))
            whenever(getMovieDetails(any())).thenReturn(fakeMediaDetail.right())
            whenever(getMediaImagesUseCase(any())).thenReturn(fakeImages.right())
            whenever(getMediaCastUseCase(any())).thenReturn(fakeCastList.right())

            val viewModel = buildViewModel()

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().contentKind).isEqualTo(fakeContentKind)
                Truth.assertThat(awaitItem().recommendations).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().similar).isNotEqualTo(expected)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().movieDetail).isEqualTo(fakeMediaDetail)
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().images).isEqualTo(fakeImages.map { it.filePath })
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().movieCredits).isEqualTo(fakeCastList)
                Truth.assertThat(awaitItem().isLoading).isFalse()
                viewModel.sendEvent(MovieDetailEvent.OnNavigate(YoutubeNavigation.Video(fakeDetails.id)))
                Truth.assertThat(awaitItem().destination)
                    .isEqualTo(YoutubeNavigation.Video(fakeDetails.id))
                cancelAndIgnoreRemainingEvents()
            }
        }

    private fun buildViewModel(
        savedStateHandle: SavedStateHandle = SavedStateHandle(
            mapOf(
                NavArgument.MEDIA_ID to fakeDetails.id
            )
        )
    ): MovieDetailViewModel {
        return MovieDetailViewModel(
            savedStateHandle,
            getMovieDetails,
            getMediaImagesUseCase,
            getMediaCastUseCase,
            getContent,
            getContentKindUseCase
        )
    }

}