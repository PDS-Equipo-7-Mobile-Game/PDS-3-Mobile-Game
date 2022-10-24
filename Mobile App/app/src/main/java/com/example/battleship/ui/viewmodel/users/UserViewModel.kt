package com.example.battleship.ui.viewmodel.users

import android.app.Activity
import android.app.Application
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.battleship.data.api.RetrofitInstance
import com.example.battleship.data.models.*
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
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


    fun getBoardState(): CoordOfPlayer?{
        var response:CoordOfPlayer? = null
        runBlocking {
            response =  RetrofitInstance.api.getBoardState(current_room!!, self_user!!.id!!.toInt()).body()
        }
        return response
    }

    fun getCurrentPlayerTurn(): String?{
        var response: String? = null
        runBlocking{
            response = RetrofitInstance.api.getCurrentPlayerTurn(current_room!!).body()?.player
        }
        return response
    }

//    fun postNewTurn(x:Int, y:Int): String{
//        var post_turn_resp: PostTurnResponse? = null
//        runBlocking {
//            var post_turn_resp = RetrofitInstance.api.postNewTurn(current_room!!, x, y, self_user!!.id!!.toInt()).body()!!
//        }
//        return post_turn_resp!!.message
//    }
}