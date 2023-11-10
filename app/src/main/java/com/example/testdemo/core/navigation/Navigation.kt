package com.example.testdemo.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testdemo.core.navigation.Destinations.START_TEST
import com.example.testdemo.core.navigation.Destinations.TEST
import com.example.testdemo.feature_test.presentation.StartTestScreen
import com.example.testdemo.feature_test.presentation.test.TestScreen
import com.example.testdemo.feature_test.presentation.test.TestViewModel

object Destinations {
    const val START_TEST = "startTest"
    const val TEST = "test"
}

@Composable
fun TestDemoNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = START_TEST,
    ) {

        composable(route = START_TEST) {
            StartTestScreen {
                navController.navigate(TEST)
            }
        }

        composable(route = TEST) {
            val testViewModel: TestViewModel = hiltViewModel()
            TestScreen(testViewModel)
        }

    }
}