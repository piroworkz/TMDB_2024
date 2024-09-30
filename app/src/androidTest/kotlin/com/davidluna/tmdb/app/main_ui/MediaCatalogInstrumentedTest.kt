package com.davidluna.tmdb.app.main_ui

import androidx.compose.ui.test.TouchInjectionScope
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.test.swipeUp
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.davidluna.tmdb.app.main_ui.common.permissions
import com.davidluna.tmdb.app.rules.MockWebServerRule
import com.davidluna.tmdb.auth_domain.entities.tags.AuthTag.AUTH_GUEST_BUTTON
import com.davidluna.tmdb.core_domain.entities.tags.CoreTag.APP_BAR_ICON
import com.davidluna.tmdb.core_domain.entities.tags.CoreTag.APP_BAR_ICON_BUTTON
import com.davidluna.tmdb.core_domain.entities.tags.CoreTag.APP_BAR_TITLE
import com.davidluna.tmdb.core_domain.entities.tags.CoreTag.APP_BAR_VIEW
import com.davidluna.tmdb.core_domain.entities.tags.CoreTag.APP_SCAFFOLD
import com.davidluna.tmdb.core_domain.entities.tags.CoreTag.MODAL_DRAWER_VIEW
import com.davidluna.tmdb.core_domain.entities.tags.CoreTag.NAV_HOST_VIEW
import com.davidluna.tmdb.core_domain.entities.tags.CoreTag.SLIDE_ANIMATED_CONTENT
import com.davidluna.tmdb.core_domain.entities.tags.CoreTag.TOP_APP_BAR
import com.davidluna.tmdb.media_domain.entities.Genre
import com.davidluna.tmdb.media_domain.entities.MediaDetails
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.FILM_IMAGE
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.FILM_IMAGE_BOX_CONTAINER
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.FILM_IMAGE_MASK
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_CATALOG_SCREEN_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAILS_RECOMMENDED_LAZY_ROW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAILS_RECOMMENDED_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAILS_SIMILAR_LAZY_ROW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAILS_SIMILAR_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAILS_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAILS_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAIL_MAIN_COLUMN
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_DETAIL_SCREEN
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_FIRST_LIST_LAZY_ROW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_FIRST_LIST_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_FOURTH_LIST_LAZY_ROW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_FOURTH_LIST_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_SECOND_LIST_LAZY_ROW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_SECOND_LIST_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_THIRD_LIST_LAZY_ROW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.MEDIA_THIRD_LIST_TITLE_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.PAGER_IMAGE
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.PLAY_TRAILER_BUTTON
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.PLAY_TRAILER_BUTTON_ICON
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.PLAY_TRAILER_BUTTON_TEXT
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.POSTERS_PAGER_VIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.REEL_IMAGE_COLUMN_CONTAINER
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.REEL_TITLE_TEXT
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.TEXT_OVERVIEW
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.TEXT_OVERVIEW_TITLE
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.TEXT_RELEASE_DATE
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.TEXT_TAGLINE
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.USER_SCORE_BOX_CONTAINER
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.USER_SCORE_LABEL
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.USER_SCORE_PERCENTAGE
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.USER_SCORE_PROGRESS
import com.davidluna.tmdb.media_domain.entities.tags.MediaTag.USER_SCORE_VIEW
import org.junit.Rule
import org.junit.Test

class MediaCatalogInstrumentedTest {

    @get:Rule(order = 0)
    val mockWebServerRule: MockWebServerRule = MockWebServerRule()

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    val grantPermissionsRule: GrantPermissionRule = GrantPermissionRule.grant(*permissions)

    @get:Rule(order = 3)
    val intentsTestRule = IntentsRule()

    @Test
    fun shouldFindAllMediaCatalogScreenTestTags(): Unit =
        with(composeTestRule) {
            onRoot(useUnmergedTree = true)
            onModalDrawerView(shouldExpectAppBar = false)
            enterAsGuest()
            onRoot(useUnmergedTree = true)
            onModalDrawerView(shouldExpectAppBar = true)
            onNodeWithTag(MEDIA_CATALOG_SCREEN_VIEW, useUnmergedTree = true)
                .assertExists()
            onReelTitleView(tag = MEDIA_FIRST_LIST_TITLE_VIEW, title = "POPULAR")
            onMediaLazyRow(tag = MEDIA_FIRST_LIST_LAZY_ROW)
            onReelTitleView(tag = MEDIA_SECOND_LIST_TITLE_VIEW, title = "TOP RATED")
            onMediaLazyRow(tag = MEDIA_SECOND_LIST_LAZY_ROW)
            onReelTitleView(tag = MEDIA_THIRD_LIST_TITLE_VIEW, title = "UPCOMING")
            onMediaLazyRow(tag = MEDIA_THIRD_LIST_LAZY_ROW)
            onNodeWithTag(MEDIA_CATALOG_SCREEN_VIEW, useUnmergedTree = true)
                .performTouchInput(TouchInjectionScope::swipeUp)
            onReelTitleView(tag = MEDIA_FOURTH_LIST_TITLE_VIEW, "NOW PLAYING")
            onMediaLazyRow(tag = MEDIA_FOURTH_LIST_LAZY_ROW)
        }

    @Test
    fun shouldFindAllMediaDetailsScreenTestTags(): Unit =
        with(composeTestRule) {
            onRoot(useUnmergedTree = true)
            enterAsGuest()
            navigateToDetail()
            onRoot(useUnmergedTree = true)
            onNodeWithTag(MEDIA_DETAIL_SCREEN)
                .assertExists()
            onNodeWithTag(MEDIA_DETAIL_MAIN_COLUMN, useUnmergedTree = true)
                .assertExists()
            onPagerView()
            onMediaDetailsView()
            onNodeWithTag(MEDIA_DETAIL_SCREEN)
                .performTouchInput(TouchInjectionScope::swipeUp)
            onReelTitleView(tag = MEDIA_DETAILS_RECOMMENDED_TITLE_VIEW, title = "RECOMMENDATIONS")
            onMediaLazyRow(tag = MEDIA_DETAILS_RECOMMENDED_LAZY_ROW)
            onReelTitleView(tag = MEDIA_DETAILS_SIMILAR_TITLE_VIEW, title = "SIMILAR")
            onMediaLazyRow(tag = MEDIA_DETAILS_SIMILAR_LAZY_ROW)
        }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.onMediaLazyRow(
        tag: String,
    ) {
        onNodeWithTag(testTag = tag, useUnmergedTree = true)
            .assertExists()
        onAllNodesWithTag(REEL_IMAGE_COLUMN_CONTAINER, useUnmergedTree = true)
            .onFirst()
            .assert(hasAnyChild(hasTestTag(FILM_IMAGE_BOX_CONTAINER)))
        onAllNodesWithTag(FILM_IMAGE_BOX_CONTAINER, useUnmergedTree = true)
            .onFirst()
            .assert(hasAnyChild(hasTestTag(FILM_IMAGE)))
            .assert(hasAnyChild(hasTestTag(FILM_IMAGE_MASK)))
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.onReelTitleView(
        tag: String,
        title: String,
    ) {
        onNodeWithTag(tag).assert(hasAnyChild(hasTestTag(REEL_TITLE_TEXT).and(hasText(title))))
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.onAppBarView() {
        onNodeWithTag(APP_BAR_VIEW, useUnmergedTree = true)
            .assertExists()
        onNodeWithTag(TOP_APP_BAR, useUnmergedTree = true)
            .assertExists()
        onNodeWithTag(APP_BAR_TITLE)
            .assertExists()
        onNodeWithTag(APP_BAR_ICON_BUTTON, useUnmergedTree = true)
            .assert(hasAnyChild(hasTestTag(APP_BAR_ICON).and(hasContentDescription("Filled.Menu"))))
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.onModalDrawerView(
        shouldExpectAppBar: Boolean,
    ) {
        onNodeWithTag(MODAL_DRAWER_VIEW)
            .assertExists()
        onNodeWithTag(APP_SCAFFOLD)
            .assertExists()
        onNodeWithTag(NAV_HOST_VIEW)
            .assertExists()
        if (shouldExpectAppBar) {
            onAppBarView()
        }

    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.onMediaDetailsView() {
        onNodeWithTag(MEDIA_DETAILS_VIEW)
            .assertExists()
        onNodeWithTag(MEDIA_DETAILS_TITLE_VIEW)
            .assert(hasAnyChild(hasTestTag(REEL_TITLE_TEXT).and(hasText(getMediaDetails().title))))
            .assertExists()
        onNodeWithTag(TEXT_RELEASE_DATE)
            .assert(
                hasText(
                    "${getMediaDetails().releaseDate} • ${
                        getMediaDetails().genres.joinToString(
                            ", "
                        ) { it.name }
                    }"
                )
            )
        onNodeWithTag(TEXT_TAGLINE)
            .assert(hasText(getMediaDetails().tagline))
        onNodeWithTag(TEXT_OVERVIEW_TITLE)
            .assert(hasText("Overview"))
        onNodeWithTag(TEXT_OVERVIEW)
            .assert(hasText(getMediaDetails().overview))
        onNodeWithTag(USER_SCORE_VIEW, useUnmergedTree = true)
            .assertExists()
            .printToLog("<-- User Score")
        onNodeWithTag(USER_SCORE_BOX_CONTAINER)
            .assertExists()
        onNodeWithTag(USER_SCORE_PROGRESS)
            .assertExists()
        onNodeWithTag(USER_SCORE_PERCENTAGE)
            .assert(hasText("77%"))
        onNodeWithTag(USER_SCORE_LABEL)
            .assert(hasText("User\nscore"))
        onNodeWithTag(PLAY_TRAILER_BUTTON, useUnmergedTree = true)
            .assert(hasAnyChild(hasTestTag(PLAY_TRAILER_BUTTON_ICON)))
            .assert(hasAnyChild(hasTestTag(PLAY_TRAILER_BUTTON_TEXT)))
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.onPagerView() {
        onNodeWithTag(SLIDE_ANIMATED_CONTENT)
            .assertExists()
        onNodeWithTag(POSTERS_PAGER_VIEW)
            .assertExists()
        onNodeWithTag(PAGER_IMAGE)
            .assertExists()
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.navigateToDetail() {
        onNodeWithTag(MEDIA_CATALOG_SCREEN_VIEW)
            .assertIsDisplayed()
        onNodeWithTag(MEDIA_FIRST_LIST_LAZY_ROW)
            .assertIsDisplayed()
        onAllNodesWithTag(REEL_IMAGE_COLUMN_CONTAINER)
            .onFirst()
            .performClick()
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.enterAsGuest() {
        onNodeWithTag(AUTH_GUEST_BUTTON)
            .assertExists()
            .performClick()
    }

    private fun getMediaDetails() = MediaDetails(
        genres = listOf(
            Genre(id = 16, name = "Animation"),
            Genre(id = 10751, name = "Family"),
            Genre(id = 12, name = "Adventure"),
            Genre(id = 35, name = "Comedy")
        ),
        id = 1022789,
        overview = "Teenager Riley's mind headquarters is undergoing a sudden demolition to make room for something entirely unexpected: new Emotions! Joy, Sadness, Anger, Fear and Disgust, who’ve long been running a successful operation by all accounts, aren’t sure how to feel when Anxiety shows up. And it looks like she’s not alone.",
        posterPath = "https://image.tmdb.org/t/p/w500/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg",
        releaseDate = "11 June, 2024",
        tagline = "Make room for new emotions.",
        title = "Inside Out 2",
        voteAverage = 7.7,
        hasVideo = true
    )
}
