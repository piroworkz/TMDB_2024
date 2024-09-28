package com.davidluna.tmdb.app.main_ui.presenter

import app.cash.turbine.test
import com.davidluna.tmdb.app.fakes.FakeMainDI
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.test_shared.fakes.fakeUnknownAppError
import com.davidluna.tmdb.test_shared.rules.CoroutineTestRule
import com.google.common.truth.Truth
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

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().loading).isTrue()
                Truth.assertThat(awaitItem().loading).isFalse()
                viewModel.sendEvent(MainEvent.SetContentKind(com.davidluna.tmdb.core_domain.entities.ContentKind.TV_SHOW))
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

    private fun buildViewModel(): MainViewModel =
        MainViewModel(
            FakeMainDI().userAccountUseCase,
            FakeMainDI().saveContentKindUseCase,
            FakeMainDI().closeSessionUseCase
        )


}