package com.example.yelpcode.ui.screens.businesses.views

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.example.yelpcode.base.BaseTestCase
import com.example.yelpcode.domain.model.BusinessModel
import com.example.yelpcode.ui.theme.YelpTheme
import org.junit.Test

class PizzaScreenKtTest : BaseTestCase() {
    @Test
    fun validate_pizza_display_state() {
        composeTestRule.setContent {    // setting our composable as content for test
            YelpTheme {
                PizzasScreen(
                    data = listOf(
                        BusinessModel(
                            id = "0",
                            name = "Something to eat",
                            imageUrl = "https://cdn4.iconfinder.com/data/icons/bettericons/354/github-512.png",
                            phone = "+1 123 456 7890",
                            address = "Somewhere over the world"
                        ),
                        BusinessModel(
                            id = "1",
                            name = "Pizza or nothing",
                            imageUrl = "https://cdn4.iconfinder.com/data/icons/bettericons/354/github-512.png",
                            phone = "+1 123 456 7891",
                            address = "Somewhere over the world"
                        )
                    ),
                    navigateToDetails = {}
                )
            }
        }
        composeTestRule.onNodeWithTag("pizzaScreen_test_TAG").assertExists()
        composeTestRule.onNodeWithTag("pizzaScreen_test_TAG").assertIsDisplayed()
    }
}