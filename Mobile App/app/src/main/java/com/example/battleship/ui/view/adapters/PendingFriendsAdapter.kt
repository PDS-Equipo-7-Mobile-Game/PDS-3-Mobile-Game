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
import com.example.battleship.ui.viewmodel.users.UserViewModel

class PendingFriendsAdapter(private val context : Activity , private val arrayList : ArrayList<Player>) : ArrayAdapter<Player>(context,
    R.layout.friends_in_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.pending_friends_in_list, null)

        val friendName : TextView = view.findViewById(R.id.pendingFriendName)

        //imageView.setImageResource(arrayList[position].imageId)
        friendName.text = arrayList[position].name
        return view
    }
}