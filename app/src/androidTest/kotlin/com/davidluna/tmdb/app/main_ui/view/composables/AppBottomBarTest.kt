package com.davidluna.tmdb.app.main_ui.view.composables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import org.junit.Rule
import org.junit.Test

class AppBottomBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun appBottomBarPreview(): Unit = composeTestRule.run {
        setContent {
            AppBottomBarPreview()
        }
        onRoot(true).printToLog("<--")
        val nowPlayingButton = onNodeWithText("NOW PLAYING")
        val popularButton = onNodeWithText("POPULAR")
        val topRatedButton = onNodeWithText("TOP RATED")

        nowPlayingButton.apply {
            assertExists()
            assertIsDisplayed()
            assertIsNotEnabled()
        }

        popularButton.apply {
            assertExists()
            assertIsDisplayed()
            assertIsEnabled()
        }

        topRatedButton.apply {
            assertExists()
            assertIsDisplayed()
            assertIsEnabled()
        }

        popularButton.performClick()

        nowPlayingButton.apply {
            assertExists()
            assertIsDisplayed()
            assertIsEnabled()
        }

        popularButton.apply {
            assertExists()
            assertIsDisplayed()
            assertIsNotEnabled()
        }

        topRatedButton.apply {
            assertExists()
            assertIsDisplayed()
            assertIsEnabled()
        }

        topRatedButton.performClick()

        nowPlayingButton.apply {
            assertExists()
            assertIsDisplayed()
            assertIsEnabled()
        }

        popularButton.apply {
            assertExists()
            assertIsDisplayed()
            assertIsEnabled()
        }

        topRatedButton.apply {
            assertExists()
            assertIsDisplayed()
            assertIsNotEnabled()
        }
    }
}