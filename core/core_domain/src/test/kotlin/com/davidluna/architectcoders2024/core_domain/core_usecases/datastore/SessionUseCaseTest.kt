package com.davidluna.architectcoders2024.core_domain.core_usecases.datastore

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class SessionUseCaseTest {

    @Mock
    lateinit var repository: PreferencesRepository

    private val useCase by lazy { SessionUseCase(repository) }

    @Test
    fun `GIVEN (invoke is called) WHEN (sessionId succeeds) THEN (should return a flow of String)`() =
        runTest {
            val expected = "sessionID"
            whenever(repository.session).thenReturn(flowOf(expected))

            val actual = useCase()

            actual.test {
                val collected = awaitItem()
                assertThat(collected).isEqualTo(expected)
                awaitComplete()
                cancel()
            }
            verify(repository).session
        }

    @Test
    fun `GIVEN (invoke is called) WHEN (saveSessionId fails) THEN (should throw Exception)`() =
        runTest {
            val expected = IOException("Test Exception")
            whenever(repository.session).thenReturn(flow { throw expected })

            val actual: Flow<String> = useCase()

            actual.test {
                val catchException = awaitError()
                assertThat(catchException).isEqualTo(expected)
                cancel()
            }
            verify(repository).session
        }
}