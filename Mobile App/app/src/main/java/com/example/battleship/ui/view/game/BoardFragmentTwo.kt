package com.example.battleship.ui.view.game

import android.R.attr.button
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.battleship.R
import com.example.battleship.data.api.RetrofitInstance
import com.example.battleship.data.models.CoordOfPlayer
import com.example.battleship.ui.viewmodel.users.UserViewModel
import kotlinx.coroutines.runBlocking


class BoardFragmentTwo : Fragment() {

    private var TABLE_HEIGHT = 18;
    private var TABLE_WIDTH = 8;

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_board_two, container, false)

        var table = view.findViewById<TableLayout>(R.id.gameTable)

        var v: View? = null
        // Populate the table with stuff

        var response: CoordOfPlayer?

        runBlocking {
            response = RetrofitInstance.api.getBoardState(userViewModel.current_room!!, userViewModel.self_user!!.id!!.toInt()).body()
            Log.d("RESPONSE: ", response.toString())
        }

        // Populate the table with stuff
        for (y in 1 until TABLE_HEIGHT + 1) {
            val r = TableRow(view.context)
            table.addView(r)
            for (x in 1 until TABLE_WIDTH + 1) {
                val b = Button(view.context)

                b.setTag("$x,$y")


                response!!.alive.forEach {

                    if (x == it.x && y == it.y){
                        b.setBackgroundColor(requireContext().getColor(R.color.green))
                    }
                }
                response!!.dead.forEach {
                    if (x == it.x && y == it.y){
                        b.setBackgroundColor(requireContext().getColor(R.color.red))
                    }
                }
                b.setOnClickListener {
                    Toast.makeText(
                        activity,
                        "You clicked ($y,$x)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                r.addView(b)
            }
        }



//        for (i in 0 until table.childCount){
//            v = table.getChildAt(i) as TableRow
//
//            for (x in 0 until v.childCount){
//                var item = v.getChildAt(x)
//                Log.d("Coordenadas de Boton: ", item.getTag().toString())
//            }
//
////            var button = v as Button
////            button.setBackgroundColor(Color.RED)
//
//        }

        return view
    }


}