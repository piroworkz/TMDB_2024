package com.davidluna.tmdb.auth_ui.view.splash

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.davidluna.tmdb.auth_ui.view.splash.holder.CurrentScreen
import com.davidluna.tmdb.auth_ui.view.splash.holder.CurrentScreen.PERMISSIONS_PROMPT
import com.davidluna.tmdb.auth_ui.view.splash.holder.CurrentScreen.SPLASH
import com.davidluna.tmdb.auth_ui.view.splash.holder.SplashAnimationState
import com.davidluna.tmdb.auth_ui.view.splash.holder.rememberSplashState
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import org.junit.Rule
import org.junit.Test

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun splashScreen_showsLogo(): Unit = composeTestRule.run {
        val currentScreen: MutableState<CurrentScreen?> = mutableStateOf(SPLASH)
        setContent(currentScreen)

        onNodeWithContentDescription("Logo")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun permissionsPrompt_showsTitle(): Unit = composeTestRule.run {
        val currentScreen: MutableState<CurrentScreen?> = mutableStateOf(PERMISSIONS_PROMPT)
        setContent(currentScreen)

        onNodeWithText("Stay in the loop, with your privacy in mind.")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun permissionsPrompt_showsDescription(): Unit = composeTestRule.run {
        val currentScreen: MutableState<CurrentScreen?> = mutableStateOf(PERMISSIONS_PROMPT)
        setContent(currentScreen)

        onNodeWithText(
            "Turn on notifications and share your approximate location to see releases and recommendations for your region. Location is used only to tailor content and can be adjusted anytime in Settings."
        ).assertExists().assertIsDisplayed()
    }

    @Test
    fun permissionsPrompt_showsButtons(): Unit = composeTestRule.run {
        val currentScreen: MutableState<CurrentScreen?> = mutableStateOf(PERMISSIONS_PROMPT)
        setContent(currentScreen)

        onNodeWithText("Not now").assertExists().assertIsDisplayed()
        onNodeWithText("Enable").assertExists().assertIsDisplayed()
    }

    @OptIn(ExperimentalSharedTransitionApi::class)
    private fun setContent(currentScreen: MutableState<CurrentScreen?>) {
        composeTestRule.setContent {
            currentScreen.value?.let { screen ->
                TmdbTheme {
                    val animationState: SplashAnimationState = rememberSplashState { }
                    SharedTransitionLayout {
                        AnimatedContent(
                            targetState = screen,
                            label = "CurrentScreen"
                        ) { target ->
                            if (target == SPLASH) {
                                SplashScreen(
                                    holder = animationState,
                                    sharedTransitionScope = this@SharedTransitionLayout,
                                    animatedVisibilityScope = this@AnimatedContent
                                )
                            } else {
                                PermissionsPromptScreen(
                                    sharedTransitionScope = this@SharedTransitionLayout,
                                    animatedVisibilityScope = this@AnimatedContent,
                                    launchPermissionsPrompt = { },
                                    onDismiss = { }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}