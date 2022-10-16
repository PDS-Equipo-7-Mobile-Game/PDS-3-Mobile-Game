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


class UserLoginFragment : Fragment() {
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
                
                var response = RetrofitInstance.api.login(Credential(login_email, login_pass))

                if (response.body()?.email?.isNotEmpty() == true){ //LogIn OK
                    Log.d("Logged In", true.toString())
                }

                else{  //Log failed
                    Log.d("Log failed", false.toString())
                }
                Log.d("RESPONSE:", response.body().toString())
            }
        }



        // Inflate the layout for this fragment
        return view
    }
}