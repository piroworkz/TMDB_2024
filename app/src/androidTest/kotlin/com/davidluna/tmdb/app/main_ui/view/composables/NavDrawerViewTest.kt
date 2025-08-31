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
import org.junit.Rule
import org.junit.Test

class NavDrawerViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun initialRender_showsUserAndItems(): Unit = composeTestRule.run {
        setContent { NavDrawerViewPreview() }

        onNodeWithContentDescription("user photo").assertIsDisplayed()
        onNodeWithText("Karina Dorsey").assertIsDisplayed()

        onNodeWithContentDescription("Outlined.Movie")
            .assertIsDisplayed()
            .assertTextEquals("Movies")
            .assertHasClickAction()
            .assertIsNotEnabled()

        onNodeWithContentDescription("Outlined.Tv")
            .assertIsDisplayed()
            .assertTextEquals("Tv Shows")
            .assertHasClickAction()
            .assertIsEnabled()

        onNodeWithContentDescription("Outlined.Close")
            .assertIsDisplayed()
            .assertTextEquals("Close Session")
            .assertHasClickAction()
            .assertIsEnabled()
    }

    @Test
    fun clickingTvShows_switchesSelection(): Unit = composeTestRule.run {
        setContent { NavDrawerViewPreview() }

        val moviesDrawerItem = onNodeWithContentDescription("Outlined.Movie")
        val tvShowsDrawerItem = onNodeWithContentDescription("Outlined.Tv")
        val closeSessionDrawerItem = onNodeWithContentDescription("Outlined.Close")

        tvShowsDrawerItem.performClick()

        moviesDrawerItem.assertIsEnabled()
        tvShowsDrawerItem.assertIsNotEnabled()
        closeSessionDrawerItem.assertIsEnabled()
    }

    @Test
    fun clickingCloseSession_closesDrawer(): Unit = composeTestRule.run {
        setContent { NavDrawerViewPreview() }

        val closeSessionDrawerItem = onNodeWithContentDescription("Outlined.Close")
        closeSessionDrawerItem.performClick()

        onRoot(useUnmergedTree = true)
            .assert(hasAnyChild(isRoot()).not())
    }
}