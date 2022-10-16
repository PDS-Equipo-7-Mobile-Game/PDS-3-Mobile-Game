package com.example.battleship.data.api

import com.example.battleship.data.models.Credential
import com.example.battleship.data.models.Player
import com.example.battleship.data.models.PlayerRegister
import retrofit2.Response
import retrofit2.http.*

interface BattleShiApi {
    @Headers("Content-Type: application/json")
    @POST("/players/login")
    suspend fun login(@Body credentials:Credential): Response<Player>


    @Headers("Content-Type: application/json")
    @POST("/player/new")
    suspend fun register(@Body player_register: PlayerRegister): Response<Player>


    @Headers("Content-Type: application/json")
    @POST("players/{player_id}/friends")
    suspend fun getFriends(@Path("player_id") player_id: Int, @Query("status") status: String ): Response<List<Player>>

    @GET("/players")
    suspend fun getPlayers(): Response<List<Player>>

    @GET("/player/{player_id}")
    suspend fun getPlayer(@Path("player_id") player_id: Int): Response<Player>

    @PATCH("/player/{player_id}/update")
    suspend fun updatePlayer(@Path("player_id") player_id: Int, @Body player: Player): Response<Player>


}