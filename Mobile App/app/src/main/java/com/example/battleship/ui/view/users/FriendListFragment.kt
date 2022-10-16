package com.example.battleship.ui.view.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.battleship.ui.view.adapters.FriendListAdapter
import com.example.battleship.MainActivity
import com.example.battleship.R
import com.example.battleship.data.models.Friend
import com.example.battleship.databinding.FragmentFriendListBinding

class FriendListFragment : Fragment() {

    private lateinit var binding : FragmentFriendListBinding
    private lateinit var friendArrayList : ArrayList<Friend>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendListBinding.inflate(layoutInflater)
        val context = context as MainActivity
        val imageId = intArrayOf(

            R.drawable.a, R.drawable.b

        )

        val name = arrayOf(

            "Helena",
            "Emilia"

        )

        friendArrayList = ArrayList()

        for ( i in name.indices){

            val friend = Friend(name[i], imageId[i])
            friendArrayList.add(friend)

        }

        binding.friendlistview.isClickable = true
        binding.friendlistview.adapter = FriendListAdapter(context, friendArrayList)

        return binding.root
    }
}