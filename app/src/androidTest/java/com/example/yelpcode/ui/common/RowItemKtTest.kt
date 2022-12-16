package com.example.yelpcode.ui.common

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import com.example.yelpcode.base.BaseTestCase
import com.example.yelpcode.domain.model.BusinessModel
import com.example.yelpcode.ui.theme.YelpTheme
import org.junit.Test

class RowItemKtTest : BaseTestCase() {

    @Test
    fun validate_data_display_when_empty_dto_is_passed() {
        composeTestRule.setContent {    // setting our composable as content for test
            YelpTheme {
                RowItem(data = BusinessModel(), onItemClick = {})
            }
        }

        composeTestRule.onNodeWithTag(
            "rowItem_businessName_test_TAG",
            useUnmergedTree = true
        ).assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            "rowItem_phone_value_test_TAG",
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    @Test
    fun validate_data_display_when_valid_dto_is_passed() {
        composeTestRule.setContent {    // setting our composable as content for test
            YelpTheme {
                RowItem(
                    data = BusinessModel(
                        name = "Pizza Place",
                        phone = "+1 123 456 7890"
                    ),
                    onItemClick = {}
                )
            }
        }

        composeTestRule.onNodeWithTag(
            "rowItem_businessName_test_TAG",
            useUnmergedTree = true
        ).assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            "rowItem_businessName_test_TAG",
            useUnmergedTree = true
        ).assertTextEquals("Pizza Place")

        composeTestRule.onNodeWithTag(
            "rowItem_phone_value_test_TAG",
            useUnmergedTree = true
        ).assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            "rowItem_phone_value_test_TAG",
            useUnmergedTree = true
        ).assertTextEquals("Ph +1 123 456 7890")
    }
}