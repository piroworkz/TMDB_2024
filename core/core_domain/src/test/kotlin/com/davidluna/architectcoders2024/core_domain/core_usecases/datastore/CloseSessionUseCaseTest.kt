package com.davidluna.architectcoders2024.core_domain.core_usecases.datastore

import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CloseSessionUseCaseTest {

    @Mock
    lateinit var repository: LocalPreferencesRepository

    @Test
    fun `given invoke() is successful when close session is called should return Boolean true`() = runTest {
        val expected: Boolean = true
        whenever(repository.closeSession()).thenReturn(expected)

        val actual = repository.closeSession()

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `given invoke() fails when close session is called should return Boolean false`() = runTest {
        val expected: Boolean = false
        whenever(repository.closeSession()).thenReturn(expected)

        val actual = repository.closeSession()

        Truth.assertThat(actual).isEqualTo(expected)
    }

}