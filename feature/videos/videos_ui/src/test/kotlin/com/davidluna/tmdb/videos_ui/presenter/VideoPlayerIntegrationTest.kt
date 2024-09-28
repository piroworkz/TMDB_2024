package com.davidluna.tmdb.videos_ui.presenter

import app.cash.turbine.test
import com.davidluna.tmdb.fakes.FakeVideosDI
import com.davidluna.tmdb.test_shared.fakes.fakeMovieVideos
import com.davidluna.tmdb.test_shared.rules.CoroutineTestRule
import com.davidluna.tmdb.videos_ui.presenter.VideoPlayerViewModel
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VideoPlayerIntegrationTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val initialState = VideoPlayerViewModel.PlayerState()
    private val mediaId = fakeMovieVideos.first().id.toInt()

    @Test
    fun `GIVEN (viewmodel init) WHEN (contentKind state = MOVIE) THEN (should fetch videos from service)`() =
        runTest {
            val viewModel = buildViewModel()

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().videos).isNotEmpty()
                Truth.assertThat(awaitItem().isLoading).isFalse()
                cancelAndIgnoreRemainingEvents()
            }
        }

    private fun buildViewModel(): VideoPlayerViewModel {
        return VideoPlayerViewModel(
            mediaId,
            FakeVideosDI().getVideosUseCase,
            FakeVideosDI().getContentKindUseCase
        )
    }

}