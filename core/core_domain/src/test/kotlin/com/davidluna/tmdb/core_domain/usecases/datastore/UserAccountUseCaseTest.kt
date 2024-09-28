package com.davidluna.tmdb.core_domain.usecases.datastore

import app.cash.turbine.test
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.test_shared.fakes.fakeUserAccount
import com.google.common.truth.Truth
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
class UserAccountUseCaseTest {

    @Mock
    lateinit var repository: PreferencesRepository

    private val useCase by lazy { UserAccountUseCase(repository) }

    @Test
    fun `GIVEN (invoke is called) WHEN (userAccount succeeds) THEN (should return a flow of UserAccount)`() =
        runTest {
            val expected = fakeUserAccount
            whenever(repository.userAccount).thenReturn(flowOf(expected))

            val actual = useCase()

            actual.test {
                val collected = awaitItem()
                Truth.assertThat(collected).isEqualTo(expected)
                awaitComplete()
                cancel()
            }
            verify(repository).userAccount
        }

    @Test
    fun `GIVEN (invoke is called) WHEN (userAccount fails) THEN (should throw Exception)`() =
        runTest {
            val expected = IOException("Test Exception")
            whenever(repository.userAccount).thenReturn(flow { throw expected })

            val actual: Flow<com.davidluna.tmdb.core_domain.entities.UserAccount> = useCase()

            actual.test {
                val catchException = awaitError()
                Truth.assertThat(catchException).isEqualTo(expected)
                cancel()
            }
            verify(repository).userAccount
        }
}