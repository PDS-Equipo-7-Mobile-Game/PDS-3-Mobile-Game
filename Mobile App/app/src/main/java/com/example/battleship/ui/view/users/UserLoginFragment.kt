package com.example.battleship.ui.view.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.battleship.R
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

        new_account_textview?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_createNewAccountFragment)
        }

        var loginButton = view.findViewById<Button>(R.id.button)

        loginButton?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_FriendListFragment)
        }

        // Inflate the layout for this fragment
        return view
    }
}