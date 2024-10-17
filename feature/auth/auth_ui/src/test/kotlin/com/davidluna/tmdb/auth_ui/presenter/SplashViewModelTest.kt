package com.davidluna.tmdb.auth_ui.presenter

import app.cash.turbine.test
import com.davidluna.tmdb.auth_ui.presenter.splash.SplashEvent
import com.davidluna.tmdb.auth_ui.presenter.splash.SplashEvent.OnBioPromptResult
import com.davidluna.tmdb.auth_ui.presenter.splash.SplashViewModel
import com.davidluna.tmdb.auth_ui.view.splash.biometrics.BioResult.SUCCESS
import com.davidluna.tmdb.core_domain.usecases.SessionFlowUseCase
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation
import com.davidluna.tmdb.test_shared.fakes.fakeEmptySession
import com.davidluna.tmdb.test_shared.fakes.fakeSession
import com.davidluna.tmdb.test_shared.rules.CoroutineTestRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var sessionFlow: SessionFlowUseCase

    private lateinit var viewModel: SplashViewModel

    private val initialState = SplashViewModel.State(permissionGranted = true)

    @Before
    fun setUp() {
        viewModel = SplashViewModel(sessionFlow)
    }

    @Test
    fun `GIVEN (viewModel is initialized) WHEN (sessionFlow collect succeeds) THEN (should update state session = Session, launchBioPrompt = true )`() =
        runTest {
            val session = fakeSession
            val expected = initialState.copy(
                session = session,
                launchBioPrompt = true,
                permissionGranted = true
            )

            whenever(sessionFlow()).thenReturn(flowOf(session))

            viewModel.onEvent(SplashEvent.OnPermissionsGranted)

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem()).isEqualTo(initialState.copy(launchBioPrompt = true))
                assertThat(awaitItem()).isEqualTo(expected)
                cancelAndIgnoreRemainingEvents()
            }
            verify(sessionFlow).invoke()
        }

    @Test
    fun `GIVEN (viewModel is initialized) WHEN (sessionFlow collect returns empty session) THEN (should update state destination = Login() )`() =
        runTest {
            val expected =
                initialState.copy(
                    destination = AuthNavigation.Login(),
                    permissionGranted = true,
                    session = fakeEmptySession
                )
            whenever(sessionFlow()).thenReturn(flowOf(fakeEmptySession))

            viewModel.onEvent(SplashEvent.OnPermissionsGranted)

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem().destination).isEqualTo(expected.destination)
                assertThat(awaitItem()).isEqualTo(expected)
                cancelAndIgnoreRemainingEvents()
            }
            verify(sessionFlow).invoke()
        }

    @Test
    fun `GIVEN (event is OnBioPromptResult) WHEN (biometrics result is SUCCESS) THEN (should update state bioResult = SUCCESS ) `() =
        runTest {
            val expected = initialState.copy(
                permissionGranted = true,
                launchBioPrompt = true,
                session = fakeSession
            )

            whenever(sessionFlow()).thenReturn(flowOf(fakeSession))

            viewModel.onEvent(SplashEvent.OnPermissionsGranted)

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem().launchBioPrompt).isTrue()
                assertThat(awaitItem()).isEqualTo(expected)
                viewModel.onEvent(OnBioPromptResult(SUCCESS))
                assertThat(awaitItem().bioResult).isEqualTo(SUCCESS)
                cancelAndConsumeRemainingEvents()
            }
        }

}