package com.example.yelpcode.ui.common

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import com.example.yelpcode.base.BaseTestCase
import com.example.yelpcode.ui.theme.YelpTheme
import org.junit.Test

class AddressRowKtTest : BaseTestCase() {

    @Test
    fun verify_initial_state() {
        composeTestRule.setContent {    // setting our composable as content for test
            YelpTheme {
                AddressRow(data = "Some address")
            }
        }

        composeTestRule.onNodeWithTag("addressRow_text_address_test_TAG").assertIsDisplayed()
        composeTestRule.onNodeWithTag("addressRow_text_address_test_TAG").assertTextEquals("Address:")

        composeTestRule.onNodeWithTag("addressRow_text_address_value_test_TAG").assertIsDisplayed()
        composeTestRule.onNodeWithTag("addressRow_text_address_value_test_TAG").assertTextEquals("Some address")
    }

}