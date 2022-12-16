package com.example.yelpcode.ui.common

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.example.yelpcode.base.BaseTestCase
import com.example.yelpcode.ui.theme.YelpTheme
import org.junit.Before
import org.junit.Test

class ArrowBackIconKtTest : BaseTestCase() {

    @Before
    fun setUp() {
        composeTestRule.setContent {    // setting our composable as content for test
            YelpTheme {
                ArrowBackIcon(onBackClick = {})
            }
        }
    }

    @Test
    fun verify_initial_state() {
        composeTestRule.onNodeWithTag("arrowBackIcon_iconButton_test_TAG").assertIsDisplayed()
    }

}