package com.example.testdemo.feature_test.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.example.testdemo.R
import com.example.testdemo.core.composables.FullWidthRoundedCornerButton

// This screen shows button to start test
@Composable
fun StartTestScreen(onClickStartTest: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        FullWidthRoundedCornerButton(
            onClick = onClickStartTest,
            title = stringResource(id = R.string.start_test).uppercase()
        )
    }
}


