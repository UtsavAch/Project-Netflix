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

@Composable
fun LoginScreen(){

    var email by remember{
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(painter = painterResource(id = R.drawable.login_image), contentDescription = "Login Image",
            modifier = Modifier.size(250.dp))
        Text(text = "Welcome back", fontSize = 28.sp, fontWeight = Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text="Login to your account")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = email, onValueChange = {
            email = it
        }, label = {Text(text="Email address")})
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = password, onValueChange = {
            password = it
        }, label = {Text(text="Password")}, visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick={Log.i("Credential", "Email: $email Password: $password")}){
            Text(text="Login")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            Text(text="Forgot password?", modifier = Modifier.clickable{})
            Text(text = " / ")
            Text(text="sign In", modifier = Modifier.clickable{})
        }
    }
}