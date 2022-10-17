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
import com.example.battleship.data.models.Player
import com.example.battleship.data.models.RespondFriendRequest
import com.example.battleship.data.models.Room
import com.example.battleship.databinding.FragmentLobbyListBinding
import com.example.battleship.ui.view.adapters.LobbyListAdapter
import com.example.battleship.ui.viewmodel.users.UserViewModel
import kotlinx.coroutines.runBlocking

class LobbyListFragment : Fragment() {

    private lateinit var binding : FragmentLobbyListBinding
    private lateinit var lobbyArrayList : ArrayList<Room>
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLobbyListBinding.inflate(layoutInflater)

        val context = context as MainActivity
        /*
        val imageId = intArrayOf(

            R.drawable.a, R.drawable.b

        )
        */
        var addLobby = binding.addlobbyButton

        addLobby.setOnClickListener {
            Log.d("Add Lobby Button: ", "Apretado")
            runBlocking {
                RetrofitInstance.api.createLobby(userViewModel.self_user!!.id!!.toInt())
                var getlobby_request = RetrofitInstance.api.getLobbies(userViewModel.self_user!!.id!!.toInt(), "accepted")
                userViewModel.lobbies = getlobby_request.body() as MutableList<Room>
            }

            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, LobbyListFragment()).commit()
        }

        var pendingInvitations = binding.pendingButton

        pendingInvitations.setOnClickListener {
            runBlocking {
                var pending_lobbies = RetrofitInstance.api.getLobbies(userViewModel.self_user!!.id!!.toInt(), "pending")
                userViewModel.pending_invitations = pending_lobbies.body() as MutableList<Room>
            }

            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, PendingRoomInvitesFragment()).commit()
        }

        lobbyArrayList = ArrayList()



        for ( i in userViewModel.lobbies){

            //button.visibility = View.INVISIBLE

            val lobby = Room(i.id, i.state)
            lobbyArrayList.add(lobby)

        }


        /*
        for (i in userViewModel.pending_friends){
            button.visibility = View.INVISIBLE
        }
        */
        binding.lobbylistview.isClickable = true
        binding.lobbylistview.adapter = LobbyListAdapter(context, lobbyArrayList, userViewModel, requireActivity().supportFragmentManager)

        return binding.root
    }
}