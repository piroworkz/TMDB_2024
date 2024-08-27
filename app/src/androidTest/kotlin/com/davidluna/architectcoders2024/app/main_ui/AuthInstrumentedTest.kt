package com.davidluna.architectcoders2024.app.main_ui

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.rule.GrantPermissionRule
import com.davidluna.architectcoders2024.app.rules.MockWebServerRule
import com.davidluna.architectcoders2024.auth_domain.entities.tags.AuthTag.AUTH_LOGIN_BUTTON
import com.davidluna.architectcoders2024.test_shared.fakes.FAKE_DEEP_LINK
import com.davidluna.architectcoders2024.test_shared.fakes.FAKE_TMDB_URL
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AuthInstrumentedTest {

    private val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val tmdbUri: Uri = Uri.parse(FAKE_TMDB_URL)
    private val deepLink: Uri = Uri.parse(FAKE_DEEP_LINK)

    @get:Rule(order = 0)
    val hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule: MockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 3)
    val grantPermissionsRule: GrantPermissionRule = GrantPermissionRule.grant(*permissions)

    @get:Rule(order = 4)
    val intentsTestRule = IntentsRule()

    @Test
    fun test(): Unit = with(composeTestRule) {
        interceptTmdbIntent()
        onRoot(true)
            .printToLog("Compose Node")
        onNodeWithTag(AUTH_LOGIN_BUTTON)
            .assertExists("Login Button not found")
            .performClick()
        waitUntil(5000) { true }
        waitForIdle()
    }

    private fun interceptTmdbIntent() {
        intending(IntentMatchers.hasData(tmdbUri))
            .respondWithFunction { deeplinkIntent() }
    }

    private fun deeplinkIntent(): ActivityResult {
        val intent = Intent(Intent.ACTION_VIEW, deepLink)
            .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        ApplicationProvider.getApplicationContext<Context>()
            .startActivity(intent)
        return ActivityResult(Activity.RESULT_OK, intent)
    }
}
