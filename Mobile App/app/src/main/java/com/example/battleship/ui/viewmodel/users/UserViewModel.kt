package com.example.battleship.ui.viewmodel.users

import android.app.Activity
import android.app.Application
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.battleship.data.models.Friend
import com.example.battleship.data.models.Player
import com.example.battleship.data.models.Room
import kotlinx.coroutines.runBlocking

class UserViewModel(application: Application): AndroidViewModel(application) {
    var friends = mutableListOf<Player>()
    var players_in_app = mutableListOf<Player>()
    var pending_friends = mutableListOf<Player>()
    var pending_invitations = mutableListOf<Room>()
    var players_in_lobby = mutableListOf<Player>()
    var self_user : Player? = null
    var friendToShow: Player? = null
    var users: MutableList<Player> = mutableListOf<Player>()
    var current_room : Int? = null
    var lobbies =  mutableListOf<Room>()

    fun createUser(email: String, password: String, name: String){
    }

    fun validateEmailAddress(email: String): Boolean{
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validatePassSecurity(password: String): Boolean{
        Log.d("Pass:", password)
        Log.d("Size pass: ", password.length.toString())
        return password.length >= 6
    }
}