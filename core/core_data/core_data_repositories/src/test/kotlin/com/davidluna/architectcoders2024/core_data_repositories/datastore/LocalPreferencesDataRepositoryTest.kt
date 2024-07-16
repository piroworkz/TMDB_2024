package com.davidluna.architectcoders2024.core_data_repositories.datastore

import app.cash.turbine.test
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.test_shared.domain.fakeContentKind
import com.davidluna.architectcoders2024.test_shared.domain.fakeUserAccount
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class LocalPreferencesDataRepositoryTest {

    @Mock
    lateinit var preferences: PreferencesDataSource

    @Test
    fun `given sessionId collect is successful when preferences sessionId is called then should return a String`() =
        runTest {
            val expected: String = "sessionID"
            whenever(preferences.sessionId).thenReturn(flowOf(expected))

            val actual = preferences.sessionId

            actual.test {
                val collected = awaitItem()
                Truth.assertThat(collected).isEqualTo(expected)
                awaitComplete()
                cancel()
            }
        }

    @Test
    fun `given sessionId collect fails when preferences sessionId is called then should throw Exception`() =
        runTest {
            val expected = IOException("Test Exception")
            whenever(preferences.sessionId).thenReturn(flow { throw expected })

            val actual = preferences.sessionId

            actual.test {
                val catchException = awaitError()
                Truth.assertThat(catchException).isEqualTo(expected)
                cancel()
            }
        }


    @Test
    fun `given userAccount collect is successful when preferences userAccount is called then should return a UserAccount`() =
        runTest {
            val expected = fakeUserAccount
            whenever(preferences.userAccount).thenReturn(flowOf(expected))

            val actual = preferences.userAccount

            actual.test {
                val collected = awaitItem()
                Truth.assertThat(collected).isEqualTo(expected)
                awaitComplete()
                cancel()
            }
        }

    @Test
    fun `given userAccount collect fails when preferences userAccount is called then should throw Exception`() =
        runTest {
            val expected = IOException("Test Exception")
            whenever(preferences.userAccount).thenReturn(flow { throw expected })

            val actual = preferences.userAccount

            actual.test {
                val catchException = awaitError()
                Truth.assertThat(catchException).isEqualTo(expected)
                cancel()
            }
        }


    @Test
    fun `given contentKind collect is successful when preferences contentKind is called then should return a UserAccount`() =
        runTest {
            val expected = fakeContentKind
            whenever(preferences.contentKind).thenReturn(flowOf(expected))

            val actual = preferences.contentKind

            actual.test {
                val collected = awaitItem()
                Truth.assertThat(collected).isEqualTo(expected)
                awaitComplete()
                cancel()
            }
        }

    @Test
    fun `given contentKind collect fails when preferences contentKind is called then should throw Exception`() =
        runTest {
            val expected = IOException("Test Exception")
            whenever(preferences.contentKind).thenReturn(flow { throw expected })

            val actual = preferences.contentKind

            actual.test {
                val catchException = awaitError()
                Truth.assertThat(catchException).isEqualTo(expected)
                cancel()
            }
        }

    @Test
    fun `given closeSession() is successful when preferences closeSession() is called then should return Boolean true`() =
        runTest {
            val expected = true
            whenever(preferences.closeSession()).thenReturn(expected)

            val actual = preferences.closeSession()

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given closeSession() fails when preferences closeSession() is called then should return Boolean false`() =
        runTest {
            val expected = false
            whenever(preferences.closeSession()).thenReturn(expected)

            val actual = preferences.closeSession()

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given saveContentKind() is successful when preferences saveContentKind() is called then should return Boolean true`() =
        runTest {
            val expected = true
            whenever(preferences.saveContentKind(any())).thenReturn(expected)

            val actual = preferences.saveContentKind(ContentKind.MOVIE)

            Truth.assertThat(actual).isEqualTo(expected)
        }

    @Test
    fun `given saveContentKind() fails when preferences saveContentKind() is called then should return Boolean false`() =
        runTest {
            val expected = false
            whenever(preferences.saveContentKind(any())).thenReturn(expected)

            val actual = preferences.saveContentKind(ContentKind.MOVIE)

            Truth.assertThat(actual).isEqualTo(expected)
        }


}