package com.example.testdemo.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testdemo.feature_test.presentation.StartTestScreen
import com.example.testdemo.feature_test.presentation.TestResultScreen
import com.example.testdemo.feature_test.presentation.test.TestScreen
import com.example.testdemo.feature_test.presentation.test.TestViewModel

enum class Screen {
    START_TEST,
    TEST,
    RESULT
}

sealed class NavigationItem(val route: String) {
    data object StartTest : NavigationItem(Screen.START_TEST.name)
    data object Test : NavigationItem(Screen.TEST.name)
    data object Result : NavigationItem(Screen.RESULT.name)
}


@Composable
fun TestDemoNavHost(
    navController: NavHostController = rememberNavController(),
) {

    val testViewModel: TestViewModel = hiltViewModel()
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
            TestScreen(
                testViewModel = testViewModel,
                onClickBack = { navController.navigateUp() },
                onClickFinishTest = { navController.navigate(NavigationItem.Result.route) },
                hasBackStackEntry = navController.previousBackStackEntry != null
            )
        }

        composable(route = NavigationItem.Result.route) {
            TestResultScreen(
                testViewModel = testViewModel,
                onClickBack = { navController.popBackStack(NavigationItem.StartTest.route, false) },
                hasBackStackEntry = navController.previousBackStackEntry != null
            )
        }
    }
}