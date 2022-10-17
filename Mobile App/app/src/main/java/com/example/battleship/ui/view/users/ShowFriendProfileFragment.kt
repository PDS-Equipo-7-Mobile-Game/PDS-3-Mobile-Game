package com.example.battleship.ui.view.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.battleship.R
import com.example.battleship.data.api.RetrofitInstance
import com.example.battleship.ui.viewmodel.users.UserViewModel
import kotlinx.coroutines.runBlocking

class ShowFriendProfileFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_friend_profile, container, false)

        val myValue = this.requireArguments().getString("message")
        Log.d("Friend to Show ID", myValue.toString())

        runBlocking {
        }

        runBlocking {
            Log.d("Friend to Show", userViewModel.friendToShow.toString())

            val friendToShow = RetrofitInstance.api.getPlayer(myValue!!.toInt()).body()


            view.findViewById<TextView>(R.id.showFriendProfileName).text = friendToShow?.name
            view.findViewById<TextView>(R.id.showFriendEmail).text = friendToShow?.email
            view.findViewById<TextView>(R.id.showFirstStatNumberTextViewOfFriend).text =
                friendToShow?.n_win_games.toString()
            view.findViewById<TextView>(R.id.showSecondStatNumberTextViewOfFriend).text =
                friendToShow?.n_lose_games.toString()
            view.findViewById<TextView>(R.id.showThirdStatNumberTextViewOfFriend).text =
                friendToShow?.n_played_games.toString()
            view.findViewById<TextView>(R.id.showFourthStatNumberTextViewOfFriend).text =
                friendToShow?.n_effectiveness.toString()
            view.findViewById<TextView>(R.id.showFifthStatNumberTextViewOfFriend).text =
                friendToShow?.n_bonifications.toString()
            view.findViewById<TextView>(R.id.showSixthStatNumberTextViewOfFriend).text =
                friendToShow?.turns_mean_of_games.toString()
            view.findViewById<TextView>(R.id.showSeventhStatNumberTextViewOfFriend).text =
                friendToShow?.mean_of_misses_by_game.toString()
        }




        return view
    }
}