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
import com.example.yelpcode.ui.common.LoadingScreen
import com.example.yelpcode.ui.common.MainTopBar
import com.example.yelpcode.ui.common.RowItem
import com.example.yelpcode.ui.common.ScrollFloatingButton
import com.example.yelpcode.ui.screens.businesses.businessError.BusinessError
import com.example.yelpcode.ui.screens.businesses.viewmodel.PizzaViewModel

@Composable
fun PizzasScreenState(
    navigateToDetails: (String) -> Unit,
    businessesViewModel: PizzaViewModel = hiltViewModel()
) {
    val state by businessesViewModel.state.collectAsState()
    businessesViewModel.fetchPizzas()
    PizzasScreen(
        navigateToDetails = navigateToDetails,
        loading = state.loading,
        data = state.data as List<BusinessModel>
    )
}

@Composable
fun PizzasScreen(
    navigateToDetails: (String) -> Unit,
    loading: Boolean,
    data: List<BusinessModel>
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
            if (loading) {
                LoadingScreen()
            }
            if (data.size == 1 && data[0].error != null) {
                BusinessError(errorType = data[0].error)
            } else {
                LazyColumn(state = state) {
                    items(data) { item ->
                        RowItem(
                            data = item,
                            onItemClick = { navigateToDetails(item.id ?: "0") }
                        )
                    }
                }
            }
            ScrollFloatingButton(state = state, coroutineScope = coroutineScope, data = data)
        }
    }

}
