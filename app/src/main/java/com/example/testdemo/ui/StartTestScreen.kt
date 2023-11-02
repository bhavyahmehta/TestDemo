package com.example.testdemo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun StartTestScreen(onClick: () -> Unit) {
    Box {
        Button(
            onClick = onClick,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "Start Test")
        }
    }
}