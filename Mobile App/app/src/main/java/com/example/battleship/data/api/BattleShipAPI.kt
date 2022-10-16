package com.example.battleship.data.api

import com.example.battleship.data.models.Credential
import com.example.battleship.data.models.Player
import retrofit2.Response
import retrofit2.http.*

interface BattleShiApi {
    @Headers("Content-Type: application/json")
    @POST("/players/login")
    suspend fun login(@Body credentials:Credential): Response<Player>

    @Headers("Content-Type: application/json")
    @POST("players/{player_id}/friends")
    suspend fun getFriends(@Path("player_id") player_id: Int, @Query("status") status: String ): Response<List<Player>>

    @GET("/players")
    suspend fun getPlayers(): Response<List<Player>>
}