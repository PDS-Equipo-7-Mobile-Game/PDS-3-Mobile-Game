package com.example.battleship.ui.view.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.fragment.app.Fragment
import com.example.battleship.R
import com.google.android.material.button.MaterialButton


class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)



        val board = view.findViewById<ConstraintLayout>(R.id.boardConstraintLayout)
        val count = board.childCount
        var v: View? = null
        for (i in 0 until count) {
            v = board.getChildAt(i)
            //do something with your child element
            Log.d("Type: ", v.id.toString())

            if (view !is Guideline) {
            }
                // do what you want with imageView
        }


        return view
    }
}