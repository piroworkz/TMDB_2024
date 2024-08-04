package com.davidluna.architectcoders2024.app.instrumented

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.davidluna.architectcoders2024.app.main_ui.MainActivity
import com.davidluna.architectcoders2024.app.utils.MockWebServerRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AuthInstrumentedTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()


    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldSignInGuestUser(): Unit = with(composeTestRule) {

    }

}