package com.davidluna.tmdb.media_ui.presenter.video_player

import app.cash.turbine.test
import com.davidluna.tmdb.fakes.FakeMediaDI
import com.davidluna.tmdb.test_shared.fakes.fakeMovieVideos
import com.davidluna.tmdb.test_shared.rules.CoroutineTestRule
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
            FakeMediaDI().getVideosUseCase,
            FakeMediaDI().getContentKindUseCase
        )
    }

}