package com.example.battleship.ui.view.users

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.battleship.R
import com.example.battleship.data.api.RetrofitInstance
import com.example.battleship.data.models.Credential
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.runBlocking
import com.example.battleship.data.models.Friend
import com.example.battleship.databinding.ActivityMainBinding


class UserLoginFragment : Fragment() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var friendArrayList : ArrayList<Friend>

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

//                POST Players hay 3 status "accepted" cuando son amigos, "pending" cuando no ha aceptado a solicitud aun y "rejected".
//                var friends_request = RetrofitInstance.api.getFriends(2, "accepted")
//                Log.d("Friends", friends_request.body().toString())

                var response = RetrofitInstance.api.login(Credential(login_email, login_pass))

                if (response.body()?.email?.isNotEmpty() == true){ //LogIn OK
                    Log.d("Logged In", true.toString())
                    Log.d("RESPONSE:", response.body().toString())


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