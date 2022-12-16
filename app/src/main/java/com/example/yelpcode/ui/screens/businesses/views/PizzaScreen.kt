@file:Suppress("UNCHECKED_CAST", "FINAL_UPPER_BOUND")

package com.example.yelpcode.ui.screens.businesses.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yelpcode.R
import com.example.yelpcode.domain.model.BusinessModel
import com.example.yelpcode.ui.app.YelpAppScreen
import com.example.yelpcode.ui.common.*
import com.example.yelpcode.ui.screens.businesses.viewmodel.BusinessesViewModel

@Composable
fun PizzasScreenState(
    navigateToDetails: (String) -> Unit,
    businessesViewModel: BusinessesViewModel = hiltViewModel()
) {
    val state by businessesViewModel.state.collectAsState()
    businessesViewModel.fetchBusiness()

    when (state) {
        is BusinessesViewModel.UIState.Loading -> {
            LoadingScreen()
        }
        is BusinessesViewModel.UIState.Success -> {
            if ((state as BusinessesViewModel.UIState.Success).places.isNotEmpty()) {
                PizzasScreen(
                    navigateToDetails = navigateToDetails,
                    data = (state as BusinessesViewModel.UIState.Success).places as List<BusinessModel>
                )
            } else {
                ErrorScreen(message = stringResource(id = R.string.there_is_no_data_available_TEXT))
            }
        }
        is BusinessesViewModel.UIState.Error -> {
            ErrorScreen(message = (state as BusinessesViewModel.UIState.Error).message)
        }
    }
}

@Composable
fun <T : BusinessModel> PizzasScreen(
    navigateToDetails: (String) -> Unit,
    data: List<T>
) {
    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    YelpAppScreen {
        Scaffold(
            topBar = {
                MainTopBar(
                    barTitle = stringResource(id = R.string.pizza_TEXT)
                )
            },
            modifier = Modifier
                .semantics(mergeDescendants = true) {}
                .testTag(stringResource(id = R.string.pizzaScreen_test_TAG))

        ) {
            LazyColumn(state = state) {
                items(data) { item ->
                    RowItem(
                        data = item,
                        onItemClick = { navigateToDetails(item.id ?: "0") }
                    )
                }
            }
            ScrollFloatingButton(state = state, coroutineScope = coroutineScope, data = data)
        }
    }
}

