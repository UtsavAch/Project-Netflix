package com.example.movieapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SignupScreen(navController: NavController){

    var username by remember{
        mutableStateOf("")
    }

    var email by remember{
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    var confirmPassword by remember{
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(text = "Welcome", fontSize = 28.sp, fontWeight = Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text="Create a new account")
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = username, onValueChange = {
            username = it
        }, label = {Text(text="Username")})
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = email, onValueChange = {
            email = it
        }, label = {Text(text="Email address")})
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = password, onValueChange = {
            password = it
        }, label = {Text(text="Password")}, visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = confirmPassword, onValueChange = {
            confirmPassword = it
        }, label = {Text(text="Confirm Password")}, visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick={Log.i("Credential", "Email: $email Password: $password")}){
            Text(text="Signup")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            Text(text="Already have an account?", modifier = Modifier.clickable{})
            Text(text = " ")
            Text(text="Login", modifier = Modifier.clickable{
                navigateToLogin(navController)
            })
        }
    }
}

//Function to navigate to Login screen
fun navigateToLogin(navController: NavController) {
    navController.navigate(Routes.login)
}
