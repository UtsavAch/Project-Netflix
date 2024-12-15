package com.example.movieapp.ui

import androidx.navigation.NavController
import com.example.movieapp.data.Routes

fun navigateToSignUp(navController: NavController) {
    navController.navigate(Routes.signup)
}

//Function to navigate to Login screen
fun navigateToLogin(navController: NavController) {
    navController.navigate(Routes.login){
        popUpTo(navController.graph.startDestinationId) { inclusive = true } // remove all screen from before
    }
}

// Function to navigate to the Home screen
fun navigateToHome(navController: NavController) {
    navController.navigate(Routes.home)
}

// Function to navigate to the Profile screen
fun navigateToProfile(navController: NavController) {
    navController.navigate(Routes.profile)
}

fun navigateToVideo(navController: NavController, videoId: Int) {
    navController.navigate("video/$videoId")
}