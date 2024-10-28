package com.example.movieapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigations(){
    val navController = rememberNavController()
    NavHost(navController= navController, startDestination = Routes.login, builder={
        composable(Routes.login){
            LoginScreen(navController)
        }
        composable(Routes.signup){
            SignupScreen(navController)
        }
    } )
}