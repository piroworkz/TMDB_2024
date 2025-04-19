package com.davidluna.tmdb.core_domain.usecases.datastore

import app.cash.turbine.test
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class GetContentKindUseCaseTest {

    @Mock
    lateinit var repository: PreferencesRepository

    private val useCase by lazy { GetContentKindUseCase(repository) }

    @Test
    fun `GIVEN (invoke is called) WHEN (get content kind succeeds) THEN (should return flow of ContentKind)`() =
        runTest {
            val expected = flowOf(com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE)
            whenever(repository.contentKind).thenReturn(expected)

            val actual: Flow<com.davidluna.tmdb.core_domain.entities.ContentKind> = useCase()

            assertThat(actual).isEqualTo(expected)
            actual.test {
                val collected = awaitItem()
                assertThat(collected).isEqualTo(com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN (invoke is called) WHEN (get content kind fails) THEN (should throw Exception)`() = runTest {
        val expected = IOException("Test Exception")
        whenever(repository.contentKind).thenReturn(flow { throw expected })

        val actual: Flow<com.davidluna.tmdb.core_domain.entities.ContentKind> = useCase()

        actual.test {
            val catchException = awaitError()
            assertThat(catchException).isEqualTo(expected)
            cancel()
        }
    }
}