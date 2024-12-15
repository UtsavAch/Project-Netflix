package com.example.movieapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.data.Routes
import com.example.movieapp.ui.HomeScreen
import com.example.movieapp.ui.LoginScreen
import com.example.movieapp.ui.ProfileScreen
import com.example.movieapp.ui.SignupScreen

@Composable
fun AppNavigations() {
    val navController = rememberNavController()
    val viewModel: AppViewModel = viewModel()
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
                LoginScreen(navController, viewModel)
            }
            composable(Routes.signup) {
                SignupScreen(navController, viewModel)
            }
            composable(Routes.home) {
                HomeScreen(navController)
            }
            composable(Routes.profile) {
                ProfileScreen(navController, modifier = Modifier, viewModel)
            }
        }
    }
}
