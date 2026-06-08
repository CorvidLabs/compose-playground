package com.corvidlabs.composeplayground

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import com.corvidlabs.composeplayground.ui.theme.ComposePlaygroundTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/// JVM (Robolectric) Compose UI tests for the home catalog and detail navigation. These run
/// under `testDebugUnitTest` with no emulator, alongside the Paparazzi snapshot suite.
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class NavigationTest {

    @get:Rule
    val composeRule = createComposeRule()

    private fun launchApp() {
        composeRule.setContent {
            ComposePlaygroundTheme(dynamicColor = false) {
                PlaygroundApp()
            }
        }
    }

    @Test
    fun homeShowsCatalog() {
        launchApp()

        composeRule.onNodeWithText("Compose Playground").assertIsDisplayed()
        composeRule.onNodeWithText("Actions").assertIsDisplayed()
        composeRule.onNodeWithText("Button").assertIsDisplayed()
    }

    @Test
    fun openComponentDetail() {
        launchApp()

        composeRule.onNodeWithText("Button").performClick()
        composeRule.waitForIdle()

        composeRule.onNodeWithText("Emphasis levels").assertIsDisplayed()
    }

    @Test
    fun searchFiltersList() {
        launchApp()

        composeRule.onNode(hasSetTextAction()).performTextInput("slider")
        composeRule.waitForIdle()

        composeRule.onNodeWithText("Slider").performScrollTo().assertIsDisplayed()
        composeRule.onNodeWithText("Button").assertDoesNotExist()
    }
}
