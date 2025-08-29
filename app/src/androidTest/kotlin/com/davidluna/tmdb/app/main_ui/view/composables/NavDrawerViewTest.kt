package com.davidluna.tmdb.app.main_ui.view.composables

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import org.junit.Rule
import org.junit.Test

class NavDrawerViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navDrawerView(): Unit = composeTestRule.run {
        setContent { TmdbTheme { NavDrawerViewPreview() } }

        val userImage = onNodeWithContentDescription("user photo")
        val userName = onNodeWithText("Karina Dorsey")
        val moviesDrawerItem = onNodeWithContentDescription("Outlined.Movie")
        val tvShowsDrawerItem = onNodeWithContentDescription("Outlined.Tv")
        val closeSessionDrawerItem = onNodeWithContentDescription("Outlined.Close")
        userImage.assertIsDisplayed()

        userName.assertIsDisplayed()

        moviesDrawerItem.apply {
            assertIsDisplayed()
            assertTextEquals("Movies")
            assertHasClickAction()
            assertIsNotEnabled()
        }

        tvShowsDrawerItem.apply {
            assertIsDisplayed()
            assertTextEquals("Tv Shows")
            assertHasClickAction()
            assertIsEnabled()
        }

        closeSessionDrawerItem.apply {
            assertIsDisplayed()
            assertTextEquals("Close Session")
            assertHasClickAction()
            assertIsEnabled()
        }

        tvShowsDrawerItem.performClick()

        moviesDrawerItem.apply {
            assertIsDisplayed()
            assertTextEquals("Movies")
            assertHasClickAction()
            assertIsEnabled()
        }

        tvShowsDrawerItem.apply {
            assertIsDisplayed()
            assertTextEquals("Tv Shows")
            assertHasClickAction()
            assertIsNotEnabled()
        }

        closeSessionDrawerItem.apply {
            assertIsDisplayed()
            assertTextEquals("Close Session")
            assertHasClickAction()
            assertIsEnabled()
        }

        closeSessionDrawerItem.performClick()

        onRoot(useUnmergedTree = true)
            .assert(hasAnyChild(isRoot()).not())
    }

}