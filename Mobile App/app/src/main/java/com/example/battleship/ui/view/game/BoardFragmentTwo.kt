package com.example.battleship.ui.view.game

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.battleship.R


class BoardFragmentTwo : Fragment() {

    private var TABLE_HEIGHT = 18;
    private var TABLE_WIDTH = 8;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_board_two, container, false)

        var table = view.findViewById<TableLayout>(R.id.gameTable)

        var v: View? = null
        // Populate the table with stuff

        // Populate the table with stuff
        for (y in 0 until TABLE_HEIGHT) {
            val r = TableRow(view.context)
            table.addView(r)
            for (x in 0 until TABLE_WIDTH) {
                val b = Button(view.context)
                b.setOnClickListener {
                    Toast.makeText(
                        activity,
                        "You clicked ($y,$x)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                b.setTag("$x,$y")
                r.addView(b)
            }
        }



        for (i in 0 until table.childCount){
            Log.d("Que es esto: ", i.toString())
            v = table.getChildAt(i)

            v.setOnClickListener {
                Log.d("Coordenadas de Boton: ", v.getTag().toString())
            }


//            var button = v as Button
//            button.setBackgroundColor(Color.RED)

        }

        return view
    }


}