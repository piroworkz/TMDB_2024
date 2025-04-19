package com.davidluna.tmdb.videos_ui.presenter

import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import com.davidluna.tmdb.core_domain.usecases.datastore.GetContentKindUseCase
import com.davidluna.tmdb.test_shared.fakes.fakeContentKind
import com.davidluna.tmdb.test_shared.fakes.fakeMovieVideos
import com.davidluna.tmdb.test_shared.fakes.fakeNotFoundAppError
import com.davidluna.tmdb.test_shared.rules.CoroutineTestRule
import com.davidluna.tmdb.videos_domain.usecases.GetVideosUseCase
import com.davidluna.tmdb.videos_ui.presenter.VideoPlayerViewModel
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class VideoPlayerViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var getVideosUseCase: GetVideosUseCase

    @Mock
    private lateinit var getContentKindUseCase: GetContentKindUseCase

    private val initialState = VideoPlayerViewModel.PlayerState()

    private val mediaId = 123456

    @Test
    fun `GIVEN (viewModel is initialized) WHEN (getContentKindUseCase succeeds) THEN (should update contentKind state = ContentKind)`() =
        runTest {
            whenever(getContentKindUseCase.invoke()).thenReturn(flowOf(fakeContentKind))
            whenever(getVideosUseCase(any())).thenReturn(fakeMovieVideos.right())

            val viewModel = buildViewModel()

            viewModel.state.test {
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

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().appError).isEqualTo(fakeNotFoundAppError)
                cancelAndIgnoreRemainingEvents()
            }

        }

    @Test
    fun `GIVEN (getContentKindUseCase succeeds) WHEN (getVideosUseCase succeeds) THEN (should update videos state = List of Video)`() =
        runTest {
            whenever(getContentKindUseCase.invoke()).thenReturn(flowOf(fakeContentKind))
            whenever(getVideosUseCase(any())).thenReturn(fakeMovieVideos.right())

            val viewModel = buildViewModel(mediaId)

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().contentKind).isEqualTo(fakeContentKind)
                Truth.assertThat(awaitItem().videos)
                    .isEqualTo(fakeMovieVideos.sortedBy { it.order }.map { it.key })
                cancelAndIgnoreRemainingEvents()
            }
        }


    @Test
    fun `GIVEN (getContentKindUseCase succeeds) WHEN (getVideosUseCase fails) THEN (should update appError state = AppError)`() =
        runTest {
            whenever(getContentKindUseCase.invoke()).thenReturn(flowOf(fakeContentKind))
            whenever(getVideosUseCase(any())).thenReturn(fakeNotFoundAppError.left())

            val viewModel = buildViewModel()

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().contentKind).isEqualTo(fakeContentKind)
                Truth.assertThat(awaitItem().appError).isEqualTo(fakeNotFoundAppError)
                cancelAndIgnoreRemainingEvents()
            }
        }


    private fun buildViewModel(mediaId: Int = -1) =
        VideoPlayerViewModel(mediaId, getVideosUseCase, getContentKindUseCase)
}