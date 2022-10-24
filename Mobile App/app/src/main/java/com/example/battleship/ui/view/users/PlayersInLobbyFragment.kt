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
import com.example.battleship.data.api.RetrofitInstance
import com.example.battleship.data.models.Friend
import com.example.battleship.data.models.Player
import com.example.battleship.databinding.FragmentFriendListBinding
import com.example.battleship.databinding.FragmentLobbyBinding
import com.example.battleship.ui.view.adapters.PlayersInLobbyAdapter
import com.example.battleship.ui.view.game.BoardFragment
import com.example.battleship.ui.view.game.BoardFragmentTwo
import com.example.battleship.ui.viewmodel.users.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.runBlocking

class PlayersInLobbyFragment : Fragment() {

    private lateinit var binding : FragmentLobbyBinding
    private lateinit var playerArrayList : ArrayList<Player>
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLobbyBinding.inflate(layoutInflater)

        val context = context as MainActivity
        /*
        val imageId = intArrayOf(

            R.drawable.a, R.drawable.b

        )
        */
        var addFriendButton = binding.root.findViewById<Button>(R.id.addFriendToLobbyButton)

        addFriendButton.setOnClickListener {

            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, AddFriendsToLobbyFragment()).commit()
        }

        var startGameButton = binding.root.findViewById<Button>(R.id.startLobbyGameButton)

        startGameButton.setOnClickListener {
            runBlocking {
                Log.d("Id sala: ", userViewModel.current_room!!.toString())
                RetrofitInstance.api.startGame(userViewModel.current_room!!.toInt())
            }
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, BoardFragmentTwo()).commit()
        }

        playerArrayList = ArrayList()

        for ( i in userViewModel.players_in_lobby){

            //button.visibility = View.INVISIBLE

            val player = Player(i.name, i.email, i.auth_key, i.id, i.n_win_games, i.n_lose_games, i.n_played_games,
                i.n_bonifications, i.n_effectiveness, i.turns_mean_of_games, i.mean_of_misses_by_game)
            playerArrayList.add(player)

        }


        /*
        for (i in userViewModel.pending_friends){
            button.visibility = View.INVISIBLE
        }
        */
        binding.playerslistview.adapter = PlayersInLobbyAdapter(context, playerArrayList, userViewModel)

        return binding.root
    }
}