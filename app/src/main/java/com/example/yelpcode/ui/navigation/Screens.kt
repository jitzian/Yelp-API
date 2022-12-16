package com.example.yelpcode.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val title: String, val icon: ImageVector? = null, val route: String) {
    object BusinessesNavScreen : Screens(
        "Pizza",
        Icons.Default.LocalPizza,
        "businesses_screen"
    )

    object BeerNavScreen : Screens(
        "Beer",
        Icons.Default.LocalDrink,
        "beers_screen"
    )

    object DetailsNavScreen : Screens(
        title = "Details",
        route = "details_screen"
    ) {
        const val id = "id"
    }

    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }
}