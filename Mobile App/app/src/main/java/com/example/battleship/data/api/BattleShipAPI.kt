package com.example.battleship.data.api

import com.example.battleship.data.models.Credential
import com.example.battleship.data.models.Player
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface BattleShiApi {
    @Headers("Content-Type: application/json")
    @POST("/players/login")
    suspend fun login(@Body credentials:Credential): Response<Player>
}