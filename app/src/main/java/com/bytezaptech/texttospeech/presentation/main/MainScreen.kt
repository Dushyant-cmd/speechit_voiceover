package com.bytezaptech.texttospeech.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bytezaptech.texttospeech.presentation.introduction.IntroductionScreen
import com.bytezaptech.texttospeech.presentation.introduction.UsecaseScreen

sealed class NavScreen(val route: String) {
    object Introduction: NavScreen("introduction_screen")
    object Usecase: NavScreen("usecase_screen")
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.Introduction.route) {
        composable(route = NavScreen.Introduction.route) { backStackEntry ->
            IntroductionScreen(modifier) {
                navController.navigate(route = NavScreen.Usecase.route)
            }
        }

        composable(route = NavScreen.Usecase.route) {
            UsecaseScreen(modifier = modifier) {
            }
        }
    }
}