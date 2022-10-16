package com.example.battleship.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: BattleShiApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://fathomless-mesa-58794.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BattleShiApi::class.java)
    }
}