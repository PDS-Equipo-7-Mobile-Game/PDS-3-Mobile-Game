package com.example.battleship.data.api

import com.example.battleship.data.models.*
import retrofit2.Response
import retrofit2.http.*

interface BattleShiApi {

    // User Management
    @Headers("Content-Type: application/json")
    @POST("/players/login")
    suspend fun login(@Body credentials:Credential): Response<Player>


    @Headers("Content-Type: application/json")
    @POST("/player/new")
    suspend fun register(@Body player_register: PlayerRegister): Response<Player>




    //Players
    @Headers("Content-Type: application/json")
    @POST("players/{player_id}/friends")
    suspend fun getFriends(@Path("player_id") player_id: Int, @Query("status") status: String ): Response<List<Player>>

    @GET("/players")
    suspend fun getPlayers(): Response<List<Player>>

    @GET("/player/{player_id}")
    suspend fun getPlayer(@Path("player_id") player_id: Int): Response<Player>

    @PATCH("/player/{player_id}/update")
    suspend fun updatePlayer(@Path("player_id") player_id: Int, @Body player: Player): Response<Player>

    @POST("/friend_request/new")
    suspend fun addFriend(@Body friend_request : FriendRequest)

    @POST("/friend_request/response")
    suspend fun respondFriendRequest(@Query("status") status: String, @Query("friend_request_id") friend_request_id: Int)

    @POST("/player/{player_id}/rooms")
    suspend fun getLobbies(@Path("player_id") player_id: Int, @Query("status") status: String): Response<List<Room>>

    @POST("player/new_room")
    suspend fun createLobby(@Query("player_id") player_id : Int): Response<Room>

    @GET("room/{room_id}/players")
    suspend fun getRoomPlayers(@Path("room_id") room_id: Int): Response<List<Player>>

    @POST("room/new_player")
    suspend fun addPlayerToLobby(@Query("room_id") room_id: Int, @Query("player_id") player_id: Int): Response<Room>

    @POST("room/{room_id}/response")
    suspend fun respondInvitation(@Path("room_id") room_id: Int, @Query("player_id") player_id: Int, @Query("status") status: String)

    @POST("new_game")
    suspend fun startGame(@Query("room_id") room_id: Int): Response<Unit>

    @POST("get_board_state")
    suspend fun getBoardState(@Query("room_id") room_id: Int, @Query("player_id") player_id: Int): Response<CoordOfPlayer>
}