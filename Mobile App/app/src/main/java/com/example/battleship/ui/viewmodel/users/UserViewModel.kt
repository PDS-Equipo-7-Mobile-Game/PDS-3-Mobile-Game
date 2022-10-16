package com.example.battleship.ui.viewmodel.users

import android.app.Activity
import android.app.Application
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.battleship.data.models.Player
import kotlinx.coroutines.runBlocking

class UserViewModel(application: Application): AndroidViewModel(application) {

    var users: MutableList<Player> = mutableListOf<Player>()

    fun createUser(email: String, password: String, name: String){
    }

    fun validateEmailAddress(email: String): Boolean{
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validatePassSecurity(password: String): Boolean{
        return password.length >= 6
    }
}