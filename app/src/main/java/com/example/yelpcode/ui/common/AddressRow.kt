package com.example.yelpcode.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.yelpcode.R

@Composable
fun AddressRow(data: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.address_TEXT),
            style = MaterialTheme.typography.caption,
            modifier = Modifier.testTag(stringResource(id = R.string.addressRow_text_address_test_TAG))
        )
        Text(
            text = data,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(stringResource(id = R.string.addressRow_text_address_value_test_TAG))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrevAddressRow() {
    AddressRow(data = "This is my address")
}