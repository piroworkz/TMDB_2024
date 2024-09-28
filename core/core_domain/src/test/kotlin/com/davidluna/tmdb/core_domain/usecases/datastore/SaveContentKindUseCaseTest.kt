package com.davidluna.tmdb.core_domain.usecases.datastore

import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SaveContentKindUseCaseTest {

    @Mock
    lateinit var repository: PreferencesRepository

    private val useCase by lazy { SaveContentKindUseCase(repository) }

    @Test
    fun `GIVEN (invoke is called) WHEN (saveContent succeeds) THEN (should return Boolean true)`() =
        runTest {
            val expected = true
            whenever(repository.saveContentKind(any())).thenReturn(expected)

            val actual: Boolean = useCase(com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE)

            assertThat(actual).isEqualTo(expected)
            verify(repository).saveContentKind(com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE)
        }

    @Test
    fun `GIVEN (invoke is called) WHEN (saveContent fails) THEN (should return Boolean false)`() =
        runTest {
            val expected = false
            whenever(repository.saveContentKind(any())).thenReturn(expected)

            val actual: Boolean = useCase(com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE)

            assertThat(actual).isEqualTo(expected)
            verify(repository).saveContentKind(com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE)
        }

}