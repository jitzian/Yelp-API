package com.example.yelpcode.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.yelpcode.ui.screens.businesses.views.BeersScreenState
import com.example.yelpcode.ui.screens.businesses.views.PizzasScreenState
import com.example.yelpcode.ui.screens.details.views.DetailsScreenState

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.BusinessesNavScreen.route
    ) {
        addBusinessesScreen(navController, this)
        addBeerScreen(navController, this)
        addDetails(navController, this)
    }
}

private fun addBusinessesScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = Screens.BusinessesNavScreen.route) {
        PizzasScreenState(
            navigateToDetails = { id ->
                navController.navigate(Screens.DetailsNavScreen.withArgs(id))
            }
        )
    }
}

private fun addBeerScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = Screens.BeerNavScreen.route) {
        BeersScreenState(
            navigateToDetails = { id ->
                navController.navigate(Screens.DetailsNavScreen.withArgs(id))
            }
        )
    }
}

private fun addDetails(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = Screens.DetailsNavScreen.withArgsFormat(
            Screens.DetailsNavScreen.id
        ),
        arguments = listOf(
            navArgument(Screens.DetailsNavScreen.id) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val args = navBackStackEntry.arguments
        DetailsScreenState(
            onBackClick = {
                navController.popBackStack()
            },
            id = args?.getString(Screens.DetailsNavScreen.id) ?: "0"
        )
    }
}

