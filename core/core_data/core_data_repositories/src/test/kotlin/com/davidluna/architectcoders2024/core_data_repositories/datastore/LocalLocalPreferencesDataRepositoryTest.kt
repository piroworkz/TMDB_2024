package com.davidluna.architectcoders2024.core_data_repositories.datastore

import app.cash.turbine.test
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.PreferencesRepository
import com.davidluna.architectcoders2024.test_shared.domain.fakeContentKind
import com.davidluna.architectcoders2024.test_shared.domain.fakeNotFoundAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeSession
import com.davidluna.architectcoders2024.test_shared.domain.fakeUserAccount
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class LocalLocalPreferencesDataRepositoryTest {

    @Mock
    lateinit var local: PreferencesDataSource

    private lateinit var repository: PreferencesRepository

    @Before
    fun setUp() {
        repository = LocalPreferencesDataRepository(local)
    }

    @Test
    fun `GIVEN (sessionId is called) WHEN (local sessionId succeeds) THEN (should return a Flow of String)`() =
        runTest {
            val expected: String = fakeSession.id
            whenever(local.session).thenReturn(flowOf(expected))

            val actual = repository.session

            actual.test {
                val collected = awaitItem()
                Truth.assertThat(collected).isEqualTo(expected)
                awaitComplete()
                cancel()
            }
            verify(local).session
        }

    @Test
    fun `GIVEN (sessionId is called) WHEN (local sessionId fails) THEN (should throw Exception)`() =
        runTest {
            val expected = fakeNotFoundAppError
            whenever(local.session).thenReturn(flow { throw expected })

            val actual = repository.session

            actual.test {
                val catchException = awaitError()
                Truth.assertThat(catchException).isEqualTo(expected)
                cancel()
            }
            verify(local).session
        }


    @Test
    fun `GIVEN (userAccount is called) WHEN (local userAccount succeeds) THEN (should return a Flow of UserAccount)`() =
        runTest {
            val expected = fakeUserAccount
            whenever(local.userAccount).thenReturn(flowOf(expected))

            val actual = repository.userAccount

            actual.test {
                val collected = awaitItem()
                Truth.assertThat(collected).isEqualTo(expected)
                awaitComplete()
                cancel()
            }
            verify(local).userAccount
        }

    @Test
    fun `GIVEN (userAccount is called) WHEN (local userAccount fails) THEN (should throw Exception)`() =
        runTest {
            val expected = IOException("Test Exception")
            whenever(local.userAccount).thenReturn(flow { throw expected })

            val actual = repository.userAccount

            actual.test {
                val catchException = awaitError()
                Truth.assertThat(catchException).isEqualTo(expected)
                cancel()
            }
            verify(local).userAccount
        }


    @Test
    fun `GIVEN (contentKind is called) WHEN (local contentKind is called) THEN (should return a Flow of ContentKind)`() =
        runTest {
            val expected = fakeContentKind
            whenever(local.contentKind).thenReturn(flowOf(expected))

            val actual = repository.contentKind

            actual.test {
                val collected = awaitItem()
                Truth.assertThat(collected).isEqualTo(expected)
                awaitComplete()
                cancel()
            }
            verify(local).contentKind
        }

    @Test
    fun `GIVEN (contentKind is called) WHEN (local contentKind fails) THEN (should throw Exception)`() =
        runTest {
            val expected = IOException("Test Exception")
            whenever(local.contentKind).thenReturn(flow { throw expected })

            val actual = repository.contentKind

            actual.test {
                val catchException = awaitError()
                Truth.assertThat(catchException).isEqualTo(expected)
                cancel()
            }
            verify(local).contentKind
        }

    @Test
    fun `GIVEN (closeSession is called) WHEN (local closeSession succeeds) THEN (should return Boolean true)`() =
        runTest {
            val expected = true
            whenever(local.closeSession()).thenReturn(expected)

            val actual = repository.closeSession()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(local).closeSession()
        }

    @Test
    fun `GIVEN (closeSession fails) WHEN (local closeSession fails) THEN (should return Boolean false)`() =
        runTest {
            val expected = false
            whenever(local.closeSession()).thenReturn(expected)

            val actual = repository.closeSession()

            Truth.assertThat(actual).isEqualTo(expected)
            verify(local).closeSession()
        }

    @Test
    fun `GIVEN (saveContentKind is called) WHEN (local saveContentKind succeeds) THEN (should return Boolean true)`() =
        runTest {
            val expected = true
            whenever(local.saveContentKind(any())).thenReturn(expected)

            val actual = repository.saveContentKind(ContentKind.MOVIE)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(local).saveContentKind(ContentKind.MOVIE)
        }

    @Test
    fun `GIVEN (saveContentKind is called) WHEN (local saveContentKind fails) THEN (should return Boolean false)`() =
        runTest {
            val expected = false
            whenever(local.saveContentKind(any())).thenReturn(expected)

            val actual = repository.saveContentKind(ContentKind.MOVIE)

            Truth.assertThat(actual).isEqualTo(expected)
            verify(local).saveContentKind(ContentKind.MOVIE)
        }


}