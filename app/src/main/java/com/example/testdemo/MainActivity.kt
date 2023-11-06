package com.example.testdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testdemo.test.presentation.StartTestScreen
import com.example.testdemo.test.presentation.TestScreen
import com.example.testdemo.test.presentation.TestViewModel
import com.example.testdemo.ui.theme.TestDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            StartTestScreen(navController)
        }

        composable(route = "test") {
            val testViewModel:TestViewModel = hiltViewModel()
            TestScreen(testViewModel)
        }
    }
}