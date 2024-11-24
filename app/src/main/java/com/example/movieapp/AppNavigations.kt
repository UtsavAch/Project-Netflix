package com.example.movieapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigations() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()) // Adds padding for system bars
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.login,
        ) {
            composable(Routes.login) {
                LoginScreen(navController)
            }
            composable(Routes.signup) {
                SignupScreen(navController)
            }
            composable(Routes.home) {
                HomeScreen(navController)
            }
            composable(Routes.trending) {
                TrendingScreen(navController)
            }
            composable(Routes.profile) {
                ProfileScreen(navController)
            }
        }
    }
}
