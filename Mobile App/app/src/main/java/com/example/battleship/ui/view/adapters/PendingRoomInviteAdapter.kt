package com.example.battleship.ui.view.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.battleship.R
import com.example.battleship.data.api.RetrofitInstance
import com.example.battleship.data.models.Room
import com.example.battleship.ui.view.users.LobbyListFragment
import com.example.battleship.ui.viewmodel.users.UserViewModel
import kotlinx.coroutines.runBlocking

class PendingRoomInviteAdapter(private val context : Activity , private val arrayList : ArrayList<Room>, private val userVM : UserViewModel, private val fragmentManager: FragmentManager) : ArrayAdapter<Room>(context,
    R.layout.pending_room_invites_in_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.pending_room_invites_in_list, null)
        val acceptReq : Button = view.findViewById(R.id.acceptInviteButton)
        val denyReq : Button = view.findViewById(R.id.denyInviteButton)

        val roomId : TextView = view.findViewById(R.id.pendingRoomInviteId)

        acceptReq.setOnClickListener {
            Log.d("Accept Button: ", "Apretado")
            Log.d("Usuario: ", userVM.self_user!!.name.toString())
            Log.d("Id: ", userVM.self_user!!.id!!.toString())
            runBlocking {
                RetrofitInstance.api.respondInvitation(arrayList[position].id, userVM.self_user!!.id!!.toInt(), "accepted")
                var getlobby_request = RetrofitInstance.api.getLobbies(userVM.self_user!!.id!!.toInt(), "accepted")
                userVM.lobbies = getlobby_request.body() as MutableList<Room>
            }
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, LobbyListFragment()).commit()

        }

        denyReq.setOnClickListener {
            runBlocking {
                RetrofitInstance.api.respondInvitation(arrayList[position].id, userVM.self_user!!.id!!.toInt(), "rejected")
            }
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, LobbyListFragment()).commit()
        }

        //imageView.setImageResource(arrayList[position].imageId)
        roomId.text = "Lobby " + arrayList[position].id.toString()
        return view
    }
}