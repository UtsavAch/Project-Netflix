package com.example.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.AppDatabase
import com.example.movieapp.data.User
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()

    // Função de cadastro (Sign Up)
    fun signUp(name: String, email: String, password: String, confirmPassword: String): Boolean {
        if (password != confirmPassword) {
            return false
        }

        viewModelScope.launch {
            val existingUser = userDao.getUserByEmail(email)
            if (existingUser == null) {
                val user = User(name = name, email = email, password = password)
                userDao.insert(user)
            } else {
                // Trate o caso de e-mail já registrado (use LiveData ou State para avisar a UI)
            }
        }
        return true
    }


    // Função de login
    fun login(email: String, password: String, onSuccess: (User) -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            val user = userDao.getUserByEmail(email)
            if (user != null && user.password == password) {
                onSuccess(user)  // Retorna o usuário autenticado
            } else {
                onFailure()  // Caso de erro, falha no login
            }
        }
    }

    // Função para atualizar a senha
    fun updatePassword(email: String, newPassword: String) {
        viewModelScope.launch {
            val user = userDao.getUserByEmail(email)
            if (user != null) {
                val updatedUser = user.copy(password = newPassword)
                userDao.updateUser(updatedUser)
            }
        }
    }

    // Função para excluir a conta do usuário
    fun deleteUser(email: String) {
        viewModelScope.launch {
            val user = userDao.getUserByEmail(email)
            if (user != null) {
                userDao.deleteUser(user)
            }
        }
    }
}
