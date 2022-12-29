@file:Suppress("FINAL_UPPER_BOUND")

package com.example.yelpcode.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.yelpcode.R
import com.example.yelpcode.constants.GlobalConstants.Companion.EMPTY_SPACE
import com.example.yelpcode.domain.model.BusinessModel

@Composable
fun RowItem(data: BusinessModel, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {}
            .padding(
                horizontal = dimensionResource(id = R.dimen.dimen_16_dp),
                vertical = dimensionResource(id = R.dimen.dimen_8_dp)
            )
            .clickable {
                onItemClick.invoke()
            },
        elevation = dimensionResource(id = R.dimen.dimen_4_dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.dimen_8_dp),
                    vertical = dimensionResource(id = R.dimen.dimen_8_dp)
                ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
        ) {
            Image(
                painter = rememberImagePainter(data = data.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.dimen_8_dp)))
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.dimen_8_dp),
                    alignment = Alignment.CenterVertically
                )
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag(stringResource(id = R.string.rowItem_businessName_test_TAG)),
                    text = data.name ?: stringResource(id = R.string.n_a_TEXT),
                    style = MaterialTheme.typography.body2
                )
                AddressRow(
                    data = data.address ?: stringResource(id = R.string.n_a_TEXT)
                )
                Text(
                    text = stringResource(id = R.string.ph_TEXT).plus(EMPTY_SPACE)
                        .plus(data.phone),
                    modifier = Modifier.testTag(stringResource(id = R.string.rowItem_phone_value_test_TAG)),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevRowItem() {
    RowItem(
        data = BusinessModel(
            name = "Name of Restaurant",
            address = "Somewhere at OH",
            phone = "+1 000 000 0000",
        ),
        onItemClick = { }
    )
}