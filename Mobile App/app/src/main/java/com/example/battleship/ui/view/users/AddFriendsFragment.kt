package com.example.battleship.ui.view.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.battleship.MainActivity
import com.example.battleship.R
import com.example.battleship.data.api.RetrofitInstance
import com.example.battleship.data.models.Credential
import com.example.battleship.data.models.FriendRequest
import com.example.battleship.data.models.Player
import com.example.battleship.databinding.FragmentAddFriendsBinding
import com.example.battleship.ui.view.adapters.AddFriendsAdapter
import com.example.battleship.ui.viewmodel.users.UserViewModel
import kotlinx.coroutines.runBlocking
import retrofit2.http.POST

class AddFriendsFragment : Fragment() {

    private lateinit var binding : FragmentAddFriendsBinding
    private lateinit var playersArrayList : ArrayList<Player>
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddFriendsBinding.inflate(layoutInflater)
        val context = context as MainActivity
        var view = inflater.inflate(R.layout.friends_in_list, container, false)
        var addFriendButton = binding.addfriendsview

        playersArrayList = ArrayList()

        for (i in userViewModel.players_in_app){

            val player = Player(i.name, i.email, i.auth_key, i.id, i.n_win_games, i.n_lose_games, i.n_played_games,
                i.n_bonifications, i.n_effectiveness, i.turns_mean_of_games, i.mean_of_misses_by_game)

            playersArrayList.add(player)
        }

        binding.addfriendsview.adapter = AddFriendsAdapter(context, playersArrayList, userViewModel)
        return binding.root
    }
}