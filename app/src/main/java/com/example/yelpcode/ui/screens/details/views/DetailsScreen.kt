package com.example.yelpcode.ui.screens.details.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.yelpcode.R
import com.example.yelpcode.domain.model.BusinessModel
import com.example.yelpcode.ui.app.YelpAppScreen
import com.example.yelpcode.ui.common.LoadingScreen
import com.example.yelpcode.ui.common.MainTopBar
import com.example.yelpcode.ui.screens.businesses.businessError.BusinessError
import com.example.yelpcode.ui.screens.details.viewmodels.DetailsViewModel

@Composable
fun DetailsScreenState(
    id: String,
    onBackClick: () -> Unit,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {

    val state by detailsViewModel.state.collectAsState()
    detailsViewModel.fetchDetailsById(id)

    DetailsScreen(
        onBackClick = onBackClick,
        loading = state.loading,
        data = state.data
    )
}

@Composable
fun DetailsScreen(
    onBackClick: () -> Unit,
    loading: Boolean,
    data: BusinessModel
) {
    YelpAppScreen {
        Scaffold(topBar = {
            MainTopBar(
                barTitle = stringResource(id = R.string.details_TEXT),
                showBackButton = true,
                onBackClick = onBackClick
            )
        }) {
            if (loading) {
                LoadingScreen()
            }

            if (data.error != null) {
                BusinessError(errorType = data.error)
            } else {
                val scrollState = rememberScrollState()
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(state = scrollState)
                            .padding(all = dimensionResource(id = R.dimen.dimen_16_dp)),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
                    ) {
                        Image(
                            painter = rememberImagePainter(data = data.imageUrl),
                            contentDescription = data.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.dimen_4_dp))),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = data.name ?: stringResource(id = R.string.n_a_TEXT),
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
        }
    }
}
