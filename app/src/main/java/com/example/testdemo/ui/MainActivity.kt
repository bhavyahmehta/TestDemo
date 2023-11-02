package com.example.testdemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testdemo.ui.theme.TestDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TestComposableApp()
                }
            }
        }
    }
}

@Composable
fun TestComposableApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "startTest") {
        composable(route = "startTest") {
            StartTestScreen(onClick = {
                navController.navigate("test")
            })
        }

        composable(route = "test") {
            TestScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestDemoTheme {
        StartTestScreen(onClick = {})
    }
}