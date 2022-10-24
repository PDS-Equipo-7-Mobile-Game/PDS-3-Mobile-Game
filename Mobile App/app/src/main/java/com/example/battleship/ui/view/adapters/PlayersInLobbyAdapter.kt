package com.example.battleship.ui.view.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.battleship.R
import com.example.battleship.data.models.Friend
import com.example.battleship.data.models.Player
import com.example.battleship.data.models.Room
import com.example.battleship.ui.viewmodel.users.UserViewModel

class PlayersInLobbyAdapter(private val context : Activity , private val arrayList : ArrayList<Player>, private val userVM : UserViewModel) : ArrayAdapter<Player>(context,
    R.layout.friends_in_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.players_in_lobby, null)


        val playerinlobbyName : TextView = view.findViewById(R.id.playerinlobbyName)

        //imageView.setImageResource(arrayList[position].imageId)
        playerinlobbyName.text = arrayList[position].name
        return view
    }
}