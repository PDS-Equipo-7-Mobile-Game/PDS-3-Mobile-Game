package com.example.battleship.ui.view.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.battleship.ui.view.adapters.FriendListAdapter
import com.example.battleship.MainActivity
import com.example.battleship.R
import com.example.battleship.data.models.Friend
import com.example.battleship.data.models.Player
import com.example.battleship.data.models.Room
import com.example.battleship.databinding.FragmentFriendListBinding
import com.example.battleship.databinding.FragmentPendingFriendsBinding
import com.example.battleship.databinding.FragmentPendingRoomInvitesBinding
import com.example.battleship.ui.view.adapters.PendingFriendsAdapter
import com.example.battleship.ui.view.adapters.PendingRoomInviteAdapter
import com.example.battleship.ui.viewmodel.users.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class PendingRoomInvitesFragment : Fragment() {

    private lateinit var binding : FragmentPendingRoomInvitesBinding
    private lateinit var pendingArrayList : ArrayList<Room>
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPendingRoomInvitesBinding.inflate(layoutInflater)

        val context = context as MainActivity

        pendingArrayList = ArrayList()


        for ( i in userViewModel.pending_invitations){

            //button.visibility = View.INVISIBLE

            val room = Room(i.id, i.state)
            pendingArrayList.add(room)

        }


        binding.pendingRoomInviteView.isClickable = true
        binding.pendingRoomInviteView.adapter = PendingRoomInviteAdapter(context, pendingArrayList, userViewModel, requireActivity().supportFragmentManager)

        return binding.root
    }
}