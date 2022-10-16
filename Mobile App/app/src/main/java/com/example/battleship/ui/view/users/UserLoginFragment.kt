package com.example.battleship.ui.view.users

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.battleship.R
import com.example.battleship.data.api.RetrofitInstance
import com.example.battleship.data.models.Credential
import com.example.battleship.data.models.Friend
import com.example.battleship.data.models.Player
import com.example.battleship.databinding.ActivityMainBinding
import com.example.battleship.ui.viewmodel.users.UserViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.runBlocking


class UserLoginFragment : Fragment() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var friendArrayList : ArrayList<Friend>

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_user_login, container, false)

        var new_account_textview = view.findViewById<TextView>(R.id.new_account_textView)
        var login_button = view.findViewById<Button>(R.id.logInButton)

        new_account_textview?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_createNewAccountFragment)
        }
        login_button.setOnClickListener {
            var login_email = view.findViewById<TextInputEditText>(R.id.login_emailTetxInputEditText).text.toString()
            var login_pass = view.findViewById<TextInputEditText>(R.id.login_passwordTextInputEditText).text.toString()

            runBlocking {

                //                    Update player
//                    var response_players = RetrofitInstance.api.updatePlayer(2,Player("Test",null,null,null,null,null,null,null,null,null)).body().toString()
//                    Log.d("Player:", response_players)

                var response = RetrofitInstance.api.login(Credential(login_email, login_pass))

                if (response.body()?.email?.isNotEmpty() == true){ //LogIn OK
                    Log.d("Logged In", true.toString())
                    Log.d("RESPONSE:", response.body().toString())

                    //                POST Players hay 3 status "accepted" cuando son amigos, "pending" cuando no ha aceptado a solicitud aun y "rejected".
                    var friends_request = RetrofitInstance.api.getFriends(2, "accepted")
                    Log.d("Friends", friends_request.body().toString())
                    userViewModel.friends = friends_request.body() as MutableList<Player>

//                    POST register
//                    var response = RetrofitInstance.api.register(PlayerRegister("Matí Bustos", "mibustos2@miuandes.cl", "1234"))
//                    Log.d("Create new user: ", response.body().toString())

//                    GET Players
//                    var response_players = RetrofitInstance.api.getPlayers().body().toString()
//                    Log.d("Players:", response_players)

                    Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_FriendListFragment)


                }

                else{  //Log failed
                    Log.d("Log failed", false.toString())
                }
            }
        }

        // Inflate the layout for this fragment
        return view
    }








}