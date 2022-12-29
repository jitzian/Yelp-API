package com.example.yelpcode.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.yelpcode.R
import com.example.yelpcode.data.remote.model.Error
import com.example.yelpcode.ui.common.ErrorScreen

@Composable
fun BusinessError(errorType: Error?) {
    when (errorType) {
        is Error.Server -> {
            ErrorScreen(
                message = stringResource(id = R.string.error_code_TEXT)
                    .plus(" ")
                    .plus(errorType.code)
            )
        }
        Error.Connectivity -> {
            ErrorScreen(
                message = stringResource(id = R.string.no_internet_connection_TEXT)
                    .plus(" ")
                    .plus((errorType as Error.Connectivity))
            )
        }
        else -> {
            ErrorScreen(message = (errorType as Error.Unknown).message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevBusinessError() {
    BusinessError(Error.Connectivity)
}