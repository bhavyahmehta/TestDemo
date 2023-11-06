package com.example.testdemo.test.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun StartTestScreen(navController: NavHostController) {
    Box {
        Button(
            onClick = { navController.navigate("test") },
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Start Test".uppercase(),
                fontSize = 16.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}