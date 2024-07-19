package com.davidluna.architectcoders2024.main_ui.presenter

import app.cash.turbine.test
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.test_shared.domain.fakeUnknownAppError
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
class MainIntegrationTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val initialState = MainViewModel.MainState()

    @Test
    fun `GIVEN (app initialization) WHEN (viewmodel receives events) THEN (should update state accordingly)`() =
        runTest {

            val viewModel = buildViewModel()
            val expectedState = initialState.copy(closeSession = true)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().loading).isTrue()
                Truth.assertThat(awaitItem().loading).isFalse()
                viewModel.sendEvent(MainEvent.SetContentKind(ContentKind.TV_SHOW))
                Truth.assertThat(awaitItem().loading).isTrue()
                Truth.assertThat(awaitItem().loading).isFalse()
                viewModel.sendEvent(MainEvent.SetAppError(fakeUnknownAppError))
                Truth.assertThat(awaitItem())
                    .isEqualTo(initialState.copy(appError = fakeUnknownAppError))
                viewModel.sendEvent(MainEvent.SetAppError(null))
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                viewModel.sendEvent(MainEvent.OnCloseSession)
                Truth.assertThat(awaitItem()).isEqualTo(expectedState)
                cancelAndIgnoreRemainingEvents()
            }
        }

    private fun buildViewModel(): MainViewModel = with(UseCasesModuleDI()) {
        MainViewModel(userAccountUseCase, saveContentKindUseCase, closeSessionUseCase)
    }

}