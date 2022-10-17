package com.example.battleship.ui.view.users

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.battleship.R
import com.example.battleship.ui.viewmodel.users.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var bottom_nav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        Log.d("Is null? : ", bottom_nav.toString())
        Log.d("Is null? : ", bottom_nav.visibility.toString())
        bottom_nav.visibility = View.VISIBLE

        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        view.findViewById<TextView>(R.id.NameTextView).setText(userViewModel.self_user?.name)
        view.findViewById<TextView>(R.id.MailTextView).setText(userViewModel.self_user?.email)
        view.findViewById<TextView>(R.id.FirstStatTextView).setText(userViewModel.self_user?.n_win_games.toString())
        view.findViewById<TextView>(R.id.SecondStatTextView).setText(userViewModel.self_user?.n_lose_games.toString())
        view.findViewById<TextView>(R.id.playedGamesTextView).setText(userViewModel.self_user?.n_played_games.toString())
        view.findViewById<TextView>(R.id.FourthStatTextView).setText(userViewModel.self_user?.n_effectiveness.toString())
        view.findViewById<TextView>(R.id.FifthStatTextView).setText(userViewModel.self_user?.n_bonifications.toString())
        view.findViewById<TextView>(R.id.SixthStatTextView).setText(userViewModel.self_user?.turns_mean_of_games.toString())
        view.findViewById<TextView>(R.id.SeventhStatTextView).setText(userViewModel.self_user?.mean_of_misses_by_game.toString())

        return view
    }

}