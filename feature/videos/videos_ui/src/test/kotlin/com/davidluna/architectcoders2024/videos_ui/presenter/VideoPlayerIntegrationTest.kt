package com.davidluna.architectcoders2024.videos_ui.presenter

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.davidluna.architectcoders2024.fakes.FakeVideosDI
import com.davidluna.architectcoders2024.navigation.domain.args.Args
import com.davidluna.architectcoders2024.test_shared.rules.CoroutineTestRule
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

    private val savedStateHandle = SavedStateHandle(mapOf(Args.DetailId.name to 1022789))

    private val initialState = VideoPlayerViewModel.PlayerState()

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
            savedStateHandle,
            FakeVideosDI().getVideosUseCase,
            FakeVideosDI().getContentKindUseCase
        )
    }

}