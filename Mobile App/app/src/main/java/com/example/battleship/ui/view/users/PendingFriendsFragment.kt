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
import com.example.battleship.databinding.FragmentFriendListBinding
import com.example.battleship.databinding.FragmentPendingFriendsBinding
import com.example.battleship.ui.view.adapters.PendingFriendsAdapter
import com.example.battleship.ui.viewmodel.users.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class PendingFriendsFragment : Fragment() {

    private lateinit var binding : FragmentPendingFriendsBinding
    private lateinit var pendingArrayList : ArrayList<Player>
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPendingFriendsBinding.inflate(layoutInflater)

        val context = context as MainActivity

        var view = inflater.inflate(R.layout.friends_in_list, container, false)

        pendingArrayList = ArrayList()

        for ( i in userViewModel.pending_friends){

            //button.visibility = View.INVISIBLE

            val friend = Player(i.name, i.email, i.auth_key, i.id, i.n_win_games, i.n_lose_games, i.n_played_games,
                i.n_bonifications, i.n_effectiveness, i.turns_mean_of_games, i.mean_of_misses_by_game)
            pendingArrayList.add(friend)

        }


        binding.pendingfriendview.isClickable = true
        binding.pendingfriendview.adapter = PendingFriendsAdapter(context, pendingArrayList)

        return binding.root
    }
}