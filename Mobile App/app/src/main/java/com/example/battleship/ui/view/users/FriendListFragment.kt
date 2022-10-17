package com.example.battleship.ui.view.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.battleship.ui.view.adapters.FriendListAdapter
import com.example.battleship.MainActivity
import com.example.battleship.R
import com.example.battleship.data.models.Friend
import com.example.battleship.data.models.Player
import com.example.battleship.databinding.FragmentFriendListBinding
import com.example.battleship.ui.viewmodel.users.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class FriendListFragment : Fragment() {

    private lateinit var binding : FragmentFriendListBinding
    private lateinit var friendArrayList : ArrayList<Player>
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendListBinding.inflate(layoutInflater)

        var bottom_nav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        Log.d("Is null? : ", bottom_nav.toString())
        Log.d("Is null? : ", bottom_nav.visibility.toString())
        bottom_nav.visibility = View.VISIBLE

        val context = context as MainActivity
        /*
        val imageId = intArrayOf(

            R.drawable.a, R.drawable.b

        )
        */

        var view = inflater.inflate(R.layout.friends_in_list, container, false)
        var button = view.findViewById<Button>(R.id.addButton)


        friendArrayList = ArrayList()

        for ( i in userViewModel.friends){

            button.visibility = View.INVISIBLE

            val friend = Player(i.name, i.email, i.auth_key, i.n_win_games, i.n_lose_games, i.n_played_games,
                                i.n_bonifications, i.n_effectiveness, i.turns_mean_of_games, i.mean_of_misses_by_game)
            friendArrayList.add(friend)

        }

        for (i in userViewModel.pending_friends){
            button.visibility = View.INVISIBLE
        }

        binding.friendlistview.isClickable = true
        binding.friendlistview.adapter = FriendListAdapter(context, friendArrayList)

        return binding.root
    }
}