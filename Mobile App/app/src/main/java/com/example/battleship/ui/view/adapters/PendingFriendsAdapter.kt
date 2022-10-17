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
import com.example.battleship.data.api.RetrofitInstance
import com.example.battleship.data.models.Friend
import com.example.battleship.data.models.Player
import com.example.battleship.data.models.RespondFriendRequest
import com.example.battleship.ui.viewmodel.users.UserViewModel
import kotlinx.coroutines.runBlocking

class PendingFriendsAdapter(private val context : Activity , private val arrayList : ArrayList<Player>, private val userVM : UserViewModel) : ArrayAdapter<Player>(context,
    R.layout.pending_friends_in_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.pending_friends_in_list, null)
        val acceptReq : Button = view.findViewById(R.id.acceptButton)
        val denyReq : Button = view.findViewById(R.id.denyButton)

        val friendName : TextView = view.findViewById(R.id.pendingFriendName)

        acceptReq.setOnClickListener {
            Log.d("Accept Button: ", "Apretado")
            runBlocking {
                RetrofitInstance.api.respondFriendRequest(RespondFriendRequest("accepted", arrayList[position].id))
                var friends_request = RetrofitInstance.api.getFriends(userVM.self_user!!.id!!.toInt(), "accepted")
                userVM.friends = friends_request.body() as MutableList<Player>
                var pending_request = RetrofitInstance.api.getFriends(userVM.self_user!!.id!!.toInt(), "pending")
                userVM.pending_friends = pending_request.body() as MutableList<Player>
            }
        }

        denyReq.setOnClickListener {
            runBlocking {
                RetrofitInstance.api.respondFriendRequest(RespondFriendRequest("rejected", arrayList[position].id))
                var pending_request = RetrofitInstance.api.getFriends(userVM.self_user!!.id!!.toInt(), "pending")
                userVM.pending_friends = pending_request.body() as MutableList<Player>
            }
        }

        //imageView.setImageResource(arrayList[position].imageId)
        friendName.text = arrayList[position].name
        return view
    }
}