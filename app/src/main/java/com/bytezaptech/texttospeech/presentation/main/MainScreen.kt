package com.bytezaptech.texttospeech.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bytezaptech.texttospeech.presentation.introduction.IntroductionScreen

sealed class NavScreen(val route: String) {
    object Introduction: NavScreen("introduction_screen")
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.Introduction.route) {
        composable(route = NavScreen.Introduction.route) {
            IntroductionScreen(modifier) {
            }
        }
    }
}