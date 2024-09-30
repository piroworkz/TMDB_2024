package com.davidluna.tmdb.app.main_ui

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.rule.GrantPermissionRule
import com.davidluna.tmdb.app.main_ui.common.permissions
import com.davidluna.tmdb.app.rules.MockWebServerRule
import com.davidluna.tmdb.auth_domain.entities.tags.AuthTag.AUTH_GUEST_BUTTON
import com.davidluna.tmdb.auth_domain.entities.tags.AuthTag.AUTH_LOGIN_BUTTON
import com.davidluna.tmdb.auth_domain.entities.tags.AuthTag.AUTH_SCREEN_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_CATALOG_SCREEN_VIEW
import com.davidluna.tmdb.test_shared.fakes.FAKE_DEEP_LINK
import com.davidluna.tmdb.test_shared.fakes.FAKE_TMDB_URL
import org.junit.Rule
import org.junit.Test

class AuthInstrumentedTest {

    private val tmdbUri: Uri = Uri.parse(FAKE_TMDB_URL)
    private val deepLink: Uri = Uri.parse(FAKE_DEEP_LINK)

    @get:Rule(order = 0)
    val mockWebServerRule: MockWebServerRule = MockWebServerRule()

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    val grantPermissionsRule: GrantPermissionRule = GrantPermissionRule.grant(*permissions)

    @get:Rule(order = 3)
    val intentsTestRule = IntentsRule()

    @Test
    fun shouldFindAllLoginScreenTestTags(): Unit =
        with(composeTestRule) {
            onRoot(true)
            onNodeWithTag(AUTH_SCREEN_VIEW)
                .assertExists()
            onNodeWithTag(AUTH_LOGIN_BUTTON)
                .assertExists()
            onNodeWithTag(AUTH_GUEST_BUTTON)
                .assertExists()
            waitForIdle()
        }

    @Test
    fun shouldGoThroughRegisteredLoginProcessThenNavigateToMediaCatalog(): Unit =
        with(composeTestRule) {
            interceptTmdbIntent()
            onRoot(true)
            onNodeWithTag(AUTH_LOGIN_BUTTON)
                .assertExists("Login Button not found")
                .performClick()
            onRoot(true)
            onNodeWithTag(MEDIA_CATALOG_SCREEN_VIEW)
                .assertIsDisplayed()
            waitForIdle()
        }

    @Test
    fun shouldGoThroughGuestLoginProcessThenNavigateToMediaCatalog(): Unit =
        with(composeTestRule) {
            onRoot(true)
            onNodeWithTag(AUTH_GUEST_BUTTON)
                .assertExists("Login Button not found")
                .performClick()
            onNodeWithTag(MEDIA_CATALOG_SCREEN_VIEW)
                .assertIsDisplayed()
            waitForIdle()
        }

    private fun interceptTmdbIntent() {
        intending(IntentMatchers.hasData(tmdbUri))
            .respondWithFunction { deeplinkIntent() }
    }

    private fun deeplinkIntent(): ActivityResult {
        val intent =
            Intent(Intent.ACTION_VIEW, deepLink)
                .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        ApplicationProvider
            .getApplicationContext<Context>()
            .startActivity(intent)
        return ActivityResult(Activity.RESULT_OK, intent)
    }
}
