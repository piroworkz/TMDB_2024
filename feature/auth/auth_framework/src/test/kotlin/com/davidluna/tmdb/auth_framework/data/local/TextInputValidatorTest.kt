package com.davidluna.tmdb.auth_framework.data.local

import com.davidluna.tmdb.auth_domain.entities.TextInputError
import com.davidluna.tmdb.auth_domain.entities.TextInputType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class TextInputValidatorTest {

    @Test
    fun `GIVEN null text WHEN validate is invoked with USERNAME type THEN it should return Required error`() {
        val sut = buildSUT()

        val result = sut.invoke(null, TextInputType.USERNAME)

        assertEquals(TextInputError.Required, result)
    }

    @Test
    fun `GIVEN blank text WHEN validate is invoked with USERNAME type THEN it should return Required error`() {
        val sut = buildSUT()

        val result = sut.invoke(" ", TextInputType.USERNAME)

        assertEquals(TextInputError.Required, result)
    }

    @Test
    fun `GIVEN invalid email WHEN validate is invoked with USERNAME type THEN it should return InvalidEmail error`() {
        val sut = buildSUT()

        val result = sut.invoke("invalidemail", TextInputType.USERNAME)

        assertEquals(TextInputError.InvalidEmail, result)
    }

    @Test
    fun `GIVEN another invalid email WHEN validate is invoked with USERNAME type THEN it should return InvalidEmail error`() {
        val sut = buildSUT()

        val result = sut.invoke("test@domain", TextInputType.USERNAME)

        assertEquals(TextInputError.InvalidEmail, result)
    }

    @Test
    fun `GIVEN valid email WHEN validate is invoked with USERNAME type THEN it should return null`() {
        val sut = buildSUT()

        val result = sut.invoke("test@example.com", TextInputType.USERNAME)

        assertNull(result)
    }

    @Test
    fun `GIVEN valid email with subdomain WHEN validate is invoked with USERNAME type THEN it should return null`() {
        val sut = buildSUT()

        val result = sut.invoke("test@sub.example.com", TextInputType.USERNAME)

        assertNull(result)
    }

    @Test
    fun `GIVEN valid email with plus addressing WHEN validate is invoked with USERNAME type THEN it should return null`() {
        val sut = buildSUT()

        val result = sut.invoke("test+alias@example.com", TextInputType.USERNAME)

        assertNull(result)
    }

    @Test
    fun `GIVEN null text WHEN validate is invoked with PASSWORD type THEN it should return Required error`() {
        val sut = buildSUT()

        val result = sut.invoke(null, TextInputType.PASSWORD)

        assertEquals(TextInputError.Required, result)
    }

    @Test
    fun `GIVEN blank text WHEN validate is invoked with PASSWORD type THEN it should return Required error`() {
        val sut = buildSUT()

        val result = sut.invoke(" ", TextInputType.PASSWORD)

        assertEquals(TextInputError.Required, result)
    }

    @Test
    fun `GIVEN short password WHEN validate is invoked with PASSWORD type THEN it should return InvalidLength error`() {
        val sut = buildSUT()

        val result = sut.invoke("short", TextInputType.PASSWORD)

        assertEquals(TextInputError.InvalidLength(8), result)
    }

    @Test
    fun `GIVEN password with exact minimum length WHEN validate is invoked with PASSWORD type THEN it should return null`() {
        val sut = buildSUT()

        val result = sut.invoke("12345678", TextInputType.PASSWORD)

        assertNull(result)
    }

    @Test
    fun `GIVEN password longer than minimum length WHEN validate is invoked with PASSWORD type THEN it should return null`() {
        val sut = buildSUT()

        val result = sut.invoke("longpassword123", TextInputType.PASSWORD)

        assertNull(result)
    }

    private fun buildSUT() = TextInputValidator()
}