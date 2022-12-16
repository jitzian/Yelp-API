package com.example.yelpcode.ui.common

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import com.example.yelpcode.base.BaseTestCase
import com.example.yelpcode.ui.theme.YelpTheme
import org.junit.Test

class ErrorScreenKtTest : BaseTestCase() {

    @Test
    fun verify_init_state() {
        composeTestRule.setContent {    // setting our composable as content for test
            YelpTheme {
                ErrorScreen(null)
            }
        }

        composeTestRule.onNodeWithTag("broken_robot_test_TAG").assertIsDisplayed()
        composeTestRule.onNodeWithTag("errorScreen_error_text_test_TAG").assertIsDisplayed()
        composeTestRule.onNodeWithTag("errorScreen_error_text_test_TAG").assertTextEquals("No Internet Connection…")
    }

    @Test
    fun verify_custom_state() {
        composeTestRule.setContent {    // setting our composable as content for test
            YelpTheme {
                ErrorScreen("This is a custom error")
            }
        }

        composeTestRule.onNodeWithTag("broken_robot_test_TAG").assertIsDisplayed()
        composeTestRule.onNodeWithTag("errorScreen_error_text_test_TAG").assertIsDisplayed()
        composeTestRule.onNodeWithTag("errorScreen_error_text_test_TAG").assertTextEquals("This is a custom error")
    }


}