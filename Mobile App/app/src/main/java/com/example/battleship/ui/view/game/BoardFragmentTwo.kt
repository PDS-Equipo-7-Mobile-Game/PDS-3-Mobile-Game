package com.example.battleship.ui.view.game

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
import com.example.battleship.data.models.PostTurnResponse
import com.example.battleship.ui.viewmodel.users.UserViewModel
import kotlinx.coroutines.runBlocking
import java.util.*


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
        var current_player_turn: String?

        runBlocking {


            response = RetrofitInstance.api.getBoardState(userViewModel.current_room!!, userViewModel.self_user!!.id!!.toInt()).body()
            current_player_turn = RetrofitInstance.api.getCurrentPlayerTurn(userViewModel.current_room!!).body()!!.player
            view.findViewById<TextView>(R.id.playerTextPH).text = current_player_turn

            Log.d("RESPONSE: ", response.toString())
            Log.d("RESPONSE: ", current_player_turn.toString())
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

                var post_turn_resp: PostTurnResponse? = null


                b.setOnClickListener {

                    runBlocking {

                        Log.d("CurrentRoom: ", userViewModel.current_room.toString())
                        Log.d("X: ", x.toString())
                        Log.d("y: ", y.toString())
                        Log.d("Player_id: ", userViewModel.self_user!!.id.toString())

                        post_turn_resp = RetrofitInstance.api.postNewTurn(userViewModel.current_room!!, x, y, userViewModel.self_user!!.id!!.toInt()).body()
                        Log.d("POSTTURN: ", post_turn_resp.toString())

                    }

                    Toast.makeText(
                        activity,
                        "${post_turn_resp?.message.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                r.addView(b)
            }
        }

//        val t = Timer()
//        t.scheduleAtFixedRate(
//            object : TimerTask() {
//                override fun run() {
//                    //Called each time when 1000 milliseconds (1 second) (the period parameter)
//                }
//            },  //Set how long before to start calling the TimerTask (in milliseconds)
//            0,  //Set the amount of time between each execution (in milliseconds)
//            5000
//        )

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