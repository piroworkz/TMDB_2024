package com.davidluna.architectcoders2024.core_domain.core_usecases.datastore

import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SaveContentKindUseCaseTest {

    @Mock
    lateinit var repository: PreferencesRepository

    @Test
    fun `GIVEN (invoke is called) WHEN (saveContent succeeds) THEN (should return Boolean true)`() =
        runTest {
            val expected: Boolean = true
            whenever(repository.saveContentKind(ContentKind.MOVIE)).thenReturn(expected)

            val actual: Boolean = repository.saveContentKind(ContentKind.MOVIE)

            assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `GIVEN (invoke is called) WHEN (saveContent fails) THEN (should return Boolean false)`() =
        runTest {
            val expected: Boolean = false
            whenever(repository.saveContentKind(ContentKind.MOVIE)).thenReturn(expected)

            val actual: Boolean = repository.saveContentKind(ContentKind.MOVIE)

            assertThat(actual).isEqualTo(expected)
        }

}