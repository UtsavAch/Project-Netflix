package com.example.movieapp.ui

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.AppViewModel
import com.example.movieapp.R
import com.example.movieapp.ui.theme.AppTheme

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AppViewModel
){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(
            painter = painterResource(id = R.drawable.baseline_play_circle_outline_24), // i think its
            contentDescription = stringResource(R.string.login_image),
            modifier = Modifier.size(250.dp)
        )
        Text(
            text = stringResource(R.string.welcome_back),
            fontSize = 28.sp,
            fontWeight = Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text= stringResource(R.string.login_to_your_account))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text= stringResource(R.string.email_address))
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {Text(text= stringResource(R.string.password))},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick={
            Log.i("Credential", "Email: $email Password: $password")
            viewModel.login(
                email,
                password,
                onSuccess = {
                    navigateToHome(navController)
                },
                onFailure = {error ->
                    errorMessage = error
                })
        }){
            Text(text= stringResource(R.string.login))
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            Text(text= stringResource(R.string.forgot_password), modifier = Modifier.clickable{})
            Text(text = stringResource(R.string.bar))
            Text(text= stringResource(R.string.sign_up), modifier = Modifier.clickable{
                navigateToSignUp(navController)
            })
        }
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage)
        }
    }
}

@Preview
@Composable
fun LoginPreview(){
    AppTheme {
        LoginScreen(rememberNavController(), viewModel())
    }
}