package com.example.battleship.ui.view.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.battleship.ui.view.adapters.FriendListAdapter
import com.example.battleship.MainActivity
import com.example.battleship.R
import com.example.battleship.data.models.Friend
import com.example.battleship.data.models.Player
import com.example.battleship.databinding.FragmentFriendListBinding
import com.example.battleship.ui.viewmodel.users.UserViewModel

class FriendListFragment : Fragment() {

    private lateinit var binding : FragmentFriendListBinding
    private lateinit var friendArrayList : ArrayList<Player>
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendListBinding.inflate(layoutInflater)
        val context = context as MainActivity
        /*
        val imageId = intArrayOf(

            R.drawable.a, R.drawable.b

        )
        */

        friendArrayList = ArrayList()

        for ( i in userViewModel.friends){

            val friend = Player(i.name, i.email, i.auth_key, i.n_win_games, i.n_lose_games, i.n_played_games,
                                i.n_bonifications, i.n_effectiveness, i.turns_mean_of_games, i.mean_of_misses_by_game)
            friendArrayList.add(friend)

        }

        binding.friendlistview.isClickable = true
        binding.friendlistview.adapter = FriendListAdapter(context, friendArrayList)

        return binding.root
    }
}