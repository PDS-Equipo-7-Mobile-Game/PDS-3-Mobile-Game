package com.example.battleship.ui.view.game

import android.app.ActionBar
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.gridlayout.widget.GridLayout
import com.example.battleship.R

class BoardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_board, container, false)

        val constraintLayout = view.findViewById<ConstraintLayout>(R.id.gameBoardConstraintLayout)

        Log.d("Grid Child Count: ", constraintLayout.childCount.toString())

        var v: View? = null

        for (i in 0 until constraintLayout.childCount){
            v = constraintLayout.getChildAt(i)

//            var button = v as Button
//            button.setBackgroundColor(Color.RED)

        }

        return view
    }
}