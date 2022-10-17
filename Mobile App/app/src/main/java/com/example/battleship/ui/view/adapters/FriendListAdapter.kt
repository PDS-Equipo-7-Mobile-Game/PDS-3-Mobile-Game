package com.example.battleship.ui.view.adapters

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.battleship.R
import com.example.battleship.data.models.Player
import com.example.battleship.ui.view.users.ShowFriendProfileFragment
import com.example.battleship.ui.viewmodel.users.UserViewModel


class FriendListAdapter(private val context : Activity , private val arrayList : ArrayList<Player>, private val userVM : UserViewModel, private val fragmentManager: FragmentManager) : ArrayAdapter<Player>(context,
    R.layout.friends_in_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.friends_in_list, null)

        val imageView : ImageView = view.findViewById(R.id.profile_pic)
        val friendName : TextView = view.findViewById(R.id.friendName)
        val addFriend : Button = view.findViewById(R.id.addButton)

        userVM.friendToShow = arrayList[position]

        val bundle = Bundle()
        val myMessage = "${arrayList[position].id}"
        bundle.putString("message", myMessage)

        friendName.setOnClickListener {
            val showFriendProfileFragment = ShowFriendProfileFragment()
            showFriendProfileFragment.setArguments(bundle)

            Log.d("PlayerToShow!", myMessage)

            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, showFriendProfileFragment).commit()
        }

        //imageView.setImageResource(arrayList[position].imageId)
        friendName.text = arrayList[position].name
        return view
    }
}