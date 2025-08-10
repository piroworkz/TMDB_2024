package com.davidluna.tmdb.auth_domain.entities

sealed class TextInputError(val message: String) {
    data class InvalidLength(val length: Int) :
        TextInputError(String.format(null, INVALID_LENGTH, length))

    data object Required : TextInputError(REQUIRED_ERROR)
    data object InvalidEmail : TextInputError(INVALID_EMAIL)
    companion object {
        private const val REQUIRED_ERROR = "This field is required"
        private const val INVALID_EMAIL = "Invalid email"
        private const val INVALID_LENGTH = "Invalid length, should be at least %d characters"
    }
}
