package com.davidluna.tmdb.core_domain.usecases.datastore

import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CloseSessionUseCaseTest {

    @Mock
    lateinit var repository: PreferencesRepository

    private val useCase by lazy { CloseSessionUseCase(repository) }

    @Test
    fun `GIVEN (invoke is called) WHEN (close session succeeds) THEN (should return Boolean true)`() =
        runTest {
            val expected = true
            whenever(repository.closeSession()).thenReturn(expected)

            val actual = useCase()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).closeSession()
        }

    @Test
    fun `GIVEN (invoke is called) WHEN (close session fails) THEN (should return Boolean false)`() =
        runTest {
            val expected = false
            whenever(repository.closeSession()).thenReturn(expected)

            val actual = useCase()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(repository).closeSession()
        }

}