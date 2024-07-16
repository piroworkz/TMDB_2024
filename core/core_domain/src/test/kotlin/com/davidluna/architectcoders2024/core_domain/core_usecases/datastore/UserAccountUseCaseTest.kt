package com.davidluna.architectcoders2024.core_domain.core_usecases.datastore

import app.cash.turbine.test
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount
import com.davidluna.architectcoders2024.test_shared.domain.fakeUserAccount
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class UserAccountUseCaseTest {

    @Mock
    lateinit var repository: LocalPreferencesRepository

    @Test
    fun `given invoke() is successful when userAccount is called then should return a flow of UserAccount`() =
        runTest {
            val expected = fakeUserAccount
            whenever(repository.userAccount).thenReturn(flowOf(expected))

            val actual = repository.userAccount

            actual.onEach { println("<-- $it") }.test {
                val collected = awaitItem()
                Truth.assertThat(collected).isEqualTo(expected)
                awaitComplete()
                cancel()
            }
        }

    @Test
    fun `given invoke() fails when userAccount is called then should throw Exception`() =
        runTest {
            val expected = IOException("Test Exception")
            whenever(repository.userAccount).thenReturn(flow { throw expected })

            val actual: Flow<UserAccount> = repository.userAccount

            actual.test {
                val catchException = awaitError()
                Truth.assertThat(catchException).isEqualTo(expected)
                cancel()
            }
        }
}