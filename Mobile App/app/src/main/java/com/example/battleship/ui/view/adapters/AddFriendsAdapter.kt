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
import androidx.lifecycle.ViewModel
import com.example.battleship.R
import com.example.battleship.data.api.RetrofitInstance
import com.example.battleship.data.models.Friend
import com.example.battleship.data.models.FriendRequest
import com.example.battleship.data.models.Player
import com.example.battleship.ui.viewmodel.users.UserViewModel
import kotlinx.coroutines.runBlocking

class AddFriendsAdapter(private val context : Activity , private val arrayList : ArrayList<Player>, private val userVM : UserViewModel) : ArrayAdapter<Player>(context,
    R.layout.friends_in_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.friends_in_list, null)

        val imageView : ImageView = view.findViewById(R.id.profile_pic)
        val playerName : TextView = view.findViewById(R.id.friendName)
        val addFriend : Button = view.findViewById(R.id.addButton)

        //imageView.setImageResource(arrayList[position].imageId)
        addFriend.setOnClickListener {
            runBlocking {
                RetrofitInstance.api.addFriend(FriendRequest(userVM.self_user?.email, arrayList[position].email))
            }
        }

        arrayList[position].id.toString()
        playerName.text = arrayList[position].name

        return view
    }
}