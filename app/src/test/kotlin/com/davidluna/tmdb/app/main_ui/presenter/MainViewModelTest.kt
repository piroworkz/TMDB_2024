package com.davidluna.tmdb.app.main_ui.presenter

import app.cash.turbine.test
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.AppErrorCode
import com.davidluna.tmdb.core_domain.usecases.datastore.CloseSessionUseCase
import com.davidluna.tmdb.core_domain.usecases.datastore.SaveContentKindUseCase
import com.davidluna.tmdb.core_domain.usecases.datastore.UserAccountUseCase
import com.davidluna.tmdb.test_shared.fakes.fakeUnknownAppError
import com.davidluna.tmdb.test_shared.fakes.fakeUserAccount
import com.davidluna.tmdb.test_shared.rules.CoroutineTestRule
import com.google.common.truth.Truth
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var userAccountUseCase: UserAccountUseCase

    @Mock
    lateinit var saveContentKindUseCase: SaveContentKindUseCase

    @Mock
    lateinit var closeSessionUseCase: CloseSessionUseCase

    private val initialState = MainViewModel.MainState()

    @Test
    fun `GIVEN (initViewModel) WHEN (userAccountUseCase is collected successfully) THEN (should update user state with UserAccount)`() =
        runTest {
            val expected = initialState.copy(user = fakeUserAccount)
            whenever(saveContentKindUseCase(any())).thenReturn(true)
            whenever(userAccountUseCase()).thenReturn(flowOf(fakeUserAccount))

            val viewModel = buildViewModel()

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().loading).isTrue()
                Truth.assertThat(awaitItem().loading).isFalse()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancel()
            }
            verify(userAccountUseCase).invoke()
            verify(saveContentKindUseCase).invoke(any())

        }

    @Test
    fun `GIVEN (initViewModel) WHEN (userAccountUseCase collection fails) THEN should update AppError state with AppError`() =
        runTest {
            whenever(saveContentKindUseCase(any())).thenReturn(true)
            whenever(userAccountUseCase()).thenReturn(flow { throw fakeUnknownAppError })
            val viewModel = buildViewModel()

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().loading).isTrue()
                Truth.assertThat(awaitItem().loading).isFalse()
                Truth.assertThat(awaitItem().appError).isNotNull()
                cancel()
            }

            verify(userAccountUseCase).invoke()
            verify(saveContentKindUseCase).invoke(any())
        }

    @Test
    fun `GIVEN (event is OnCloseSession) WHEN (closeSessionUseCase is successful) THEN (should update closeSession state to true)`() =
        runTest {
            whenever(saveContentKindUseCase(any())).thenReturn(true)
            whenever(userAccountUseCase()).thenReturn(flowOf(fakeUserAccount))
            whenever(closeSessionUseCase()).thenReturn(true)

            val viewModel = buildViewModel()

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().loading).isTrue()
                Truth.assertThat(awaitItem().loading).isFalse()
                Truth.assertThat(awaitItem().user).isEqualTo(fakeUserAccount)
                viewModel.sendEvent(MainEvent.OnCloseSession)
                Truth.assertThat(awaitItem().closeSession).isTrue()
                cancel()
            }
            verify(userAccountUseCase).invoke()
            verify(saveContentKindUseCase).invoke(any())
            verify(closeSessionUseCase).invoke()

        }

    @Test
    fun `GIVEN (event is OnCloseSession) WHEN (closeSessionUseCase fails) THEN (should update appError state to AppError)`() =
        runTest {
            val expected = initialState.copy(
                user = fakeUserAccount,
                closeSession = false,
                appError = AppError.Message(
                    AppErrorCode.UNKNOWN,
                    "Error closing session, please try again later"
                )
            )
            whenever(saveContentKindUseCase(any())).thenReturn(true)
            whenever(userAccountUseCase()).thenReturn(flowOf(fakeUserAccount))
            whenever(closeSessionUseCase()).thenReturn(false)

            val viewModel = buildViewModel()

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().loading).isTrue()
                Truth.assertThat(awaitItem().loading).isFalse()
                Truth.assertThat(awaitItem().user).isEqualTo(fakeUserAccount)
                viewModel.sendEvent(MainEvent.OnCloseSession)
                Truth.assertThat(awaitItem()).isEqualTo(expected)
            }
            verify(userAccountUseCase).invoke()
            verify(saveContentKindUseCase).invoke(any())
            verify(closeSessionUseCase).invoke()

        }


    private fun buildViewModel(): MainViewModel {
        return MainViewModel(
            userAccountUseCase,
            saveContentKindUseCase,
            closeSessionUseCase
        )
    }
}