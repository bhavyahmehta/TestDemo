package com.example.testdemo.feature_test.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testdemo.R

@Composable
fun StartTestScreen(onNavigateToTest: () -> Unit) {
    Box {
        Button(
            onClick =  onNavigateToTest ,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.start_test).uppercase(),
                fontSize = 16.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}