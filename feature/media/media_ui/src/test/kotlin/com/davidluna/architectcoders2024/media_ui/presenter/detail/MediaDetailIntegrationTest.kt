package com.davidluna.architectcoders2024.media_ui.presenter.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.davidluna.architectcoders2024.media_ui.presenter.empty
import com.davidluna.architectcoders2024.navigation.domain.args.Args
import com.davidluna.architectcoders2024.test_shared_framework.integration.di.UseCasesModuleDI
import com.davidluna.architectcoders2024.test_shared_framework.rules.CoroutineTestRule
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.onEach
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
    private val savedStateHandle = SavedStateHandle(mapOf(Args.DetailId.name to 1022789))


    @Test
    fun `GIVEN (content kind is not null) WHEN (viewModel init) THEN (should fetch data from service)`() =
        runTest {
            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().contentKind).isNotNull()
                Truth.assertThat(awaitItem().recommendations).isNotEqualTo(empty)
                Truth.assertThat(awaitItem().similar).isNotEqualTo(empty)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().movieDetail).isNotNull()
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().images).isNotNull()
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().movieCredits).isNotNull()
                Truth.assertThat(awaitItem().isLoading).isFalse()
                cancelAndIgnoreRemainingEvents()
            }

        }

    private fun buildViewModel(savedStateHandle: SavedStateHandle) =
        MovieDetailViewModel(
            savedStateHandle = savedStateHandle,
            getMovieDetails = UseCasesModuleDI().getMediaDetailsUseCase,
            getMediaImagesUseCase = UseCasesModuleDI().getMediaImagesUseCase,
            getMediaCastUseCase = UseCasesModuleDI().getMediaCastUseCase,
            getContent = UseCasesModuleDI().getMediaCatalogUseCase,
            getContentKindUseCase = UseCasesModuleDI().getContentKindUseCase
        )

}