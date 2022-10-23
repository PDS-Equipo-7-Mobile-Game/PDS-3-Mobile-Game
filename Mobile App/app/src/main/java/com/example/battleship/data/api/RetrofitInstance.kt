package com.example.battleship.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    //Este metodo los va a ayudar a debugear las request que hacen
    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(makeLoggingInterceptor(isDebug = true))
        .build()

    val api: BattleShiApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://fathomless-mesa-58794.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client) // con el client decimos cuales van a ser los headers
            .build()
            .create(BattleShiApi::class.java)
    }
}