package com.example.movieapp.ui

import androidx.navigation.NavController
import com.example.movieapp.data.Routes

fun navigateToSignUp(navController: NavController) {
    navController.navigate(Routes.signup)
}

//Function to navigate to Login screen
fun navigateToLogin(navController: NavController) {
    navController.navigate(Routes.login){
        popUpTo(0) // removes all screens, for example in logout
    }
}

// Function to navigate to the Home screen
fun navigateToHome(navController: NavController) {
    navController.navigate(Routes.home)
}

// Function to navigate to the Trending screen
fun navigateToTrending(navController: NavController) {
    navController.navigate(Routes.trending)
}

// Function to navigate to the Profile screen
fun navigateToProfile(navController: NavController) {
    navController.navigate(Routes.profile)
}
