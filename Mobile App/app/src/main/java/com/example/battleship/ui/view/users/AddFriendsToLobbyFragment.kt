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
import com.example.battleship.databinding.AddFriendsToLobbyBinding
import com.example.battleship.databinding.FragmentFriendListBinding
import com.example.battleship.ui.view.adapters.AddFriendsToLobbyAdapter
import com.example.battleship.ui.viewmodel.users.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class AddFriendsToLobbyFragment : Fragment() {

    private lateinit var binding : AddFriendsToLobbyBinding
    private lateinit var playersArrayList : ArrayList<Player>
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddFriendsToLobbyBinding.inflate(layoutInflater)

        val context = context as MainActivity
        /*
        val imageId = intArrayOf(

            R.drawable.a, R.drawable.b

        )
        */

        playersArrayList = ArrayList()

        for ( i in userViewModel.players_in_app){

            //button.visibility = View.INVISIBLE

            val players = Player(i.name, i.email, i.auth_key, i.id, i.n_win_games, i.n_lose_games, i.n_played_games,
                i.n_bonifications, i.n_effectiveness, i.turns_mean_of_games, i.mean_of_misses_by_game)
            playersArrayList.add(players)

        }


        /*
        for (i in userViewModel.pending_friends){
            button.visibility = View.INVISIBLE
        }
        */
        binding.addfriendsview.isClickable = true
        binding.addfriendsview.adapter = AddFriendsToLobbyAdapter(context, playersArrayList, userViewModel)

        return binding.root
    }
}