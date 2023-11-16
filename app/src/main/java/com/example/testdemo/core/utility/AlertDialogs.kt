package com.example.testdemo.core.utility

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.testdemo.R
import com.example.testdemo.core.extensions.isValid
import com.example.testdemo.ui.theme.TestDemoTheme

@Composable
fun ShowAlertDialog(
    title: String?,
    description: String?,
    confirmButtonText: String? = stringResource(id = R.string.yes),
    dismissButtonText: String? = stringResource(id = R.string.no),
    onConfirmButtonClick: () -> Unit,
    onDismissButtonClick: () -> Unit,
    onDismissDialog: () -> Unit,
) {
    TestDemoTheme {
        Column {
            AlertDialog(
                onDismissRequest = {
                    onDismissDialog()
                },
                title = {
                    if (title.isValid()) {
                        Text(text = title!!)
                    }
                },
                text = {
                    if (description.isValid()) {
                        Text(text = description!!)
                    }
                },
                confirmButton = {
                    if (confirmButtonText.isValid()) {
                        TextButton(
                            onClick = {
                                onConfirmButtonClick()
                            }) {
                            Text(text = confirmButtonText!!)
                        }
                    }
                },
                dismissButton = {
                    if (dismissButtonText.isValid()) {
                        TextButton(
                            onClick = {
                                onDismissButtonClick()
                            }) {
                            Text(text = dismissButtonText!!)
                        }
                    }
                }
            )
        }
    }
}
