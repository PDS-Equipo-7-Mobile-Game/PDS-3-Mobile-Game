package com.example.battleship.ui.view.adapters

import android.app.Activity
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
import com.example.battleship.data.api.RetrofitInstance
import com.example.battleship.data.models.Friend
import com.example.battleship.data.models.Player
import com.example.battleship.data.models.RespondFriendRequest
import com.example.battleship.data.models.Room
import com.example.battleship.ui.view.users.LobbyListFragment
import com.example.battleship.ui.view.users.PlayersInLobbyFragment
import com.example.battleship.ui.viewmodel.users.UserViewModel
import kotlinx.coroutines.runBlocking

class LobbyListAdapter(private val context : Activity , private val arrayList : ArrayList<Room>, private val userVM : UserViewModel, private val fragmentManager: FragmentManager) : ArrayAdapter<Room>(context,
    R.layout.lobbies_in_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.lobbies_in_list, null)


        val lobbyName : TextView = view.findViewById(R.id.lobbyName)

        lobbyName.setOnClickListener {
            runBlocking {
                var players_in_lobby = RetrofitInstance.api.getRoomPlayers(arrayList[position].id)
                userVM.players_in_lobby = players_in_lobby.body() as MutableList<Player>
                userVM.current_room = arrayList[position].id
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, PlayersInLobbyFragment()).commit()
            }
        }

        //imageView.setImageResource(arrayList[position].imageId)
        lobbyName.text = "Lobby " + arrayList[position].id.toString()
        return view
    }
}