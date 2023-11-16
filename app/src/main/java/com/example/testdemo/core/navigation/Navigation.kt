package com.example.testdemo.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testdemo.feature_test.presentation.StartTestScreen
import com.example.testdemo.feature_test.presentation.test.TestScreen
import com.example.testdemo.feature_test.presentation.test.TestViewModel

enum class Screen {
    START_TEST,
    TEST,
}

sealed class NavigationItem(val route: String) {
    data object StartTest : NavigationItem(Screen.START_TEST.name)
    data object Test : NavigationItem(Screen.TEST.name)
}


@Composable
fun TestDemoNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.StartTest.route,
    ) {

        composable(route = NavigationItem.StartTest.route) {
            StartTestScreen(
                onClickStartTest = { navController.navigate(NavigationItem.Test.route) }
            )
        }

        composable(route = NavigationItem.Test.route) {
            val testViewModel: TestViewModel = hiltViewModel()
            TestScreen(testViewModel = testViewModel,
                onClickBack = { navController.navigateUp() })
        }
    }
}