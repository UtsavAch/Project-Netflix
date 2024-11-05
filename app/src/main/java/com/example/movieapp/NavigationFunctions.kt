package com.example.movieapp

import androidx.navigation.NavController

fun navigateToSignUp(navController: NavController) {
    navController.navigate(Routes.signup)
}

//Function to navigate to Login screen
fun navigateToLogin(navController: NavController) {
    navController.navigate(Routes.login)
}

// Function to navigate to the Home screen
fun navigateToHome(navController: NavController) {
    navController.navigate(Routes.home)
}

// Function to navigate to the Home screen
fun navigateToTrending(navController: NavController) {
    navController.navigate(Routes.trending)
}

// Function to navigate to the Home screen
fun navigateToProfile(navController: NavController) {
    navController.navigate(Routes.profile)
}
