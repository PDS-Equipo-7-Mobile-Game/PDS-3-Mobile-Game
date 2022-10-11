package com.example.battleship.data.api

import com.example.battleship.data.models.Todo
import retrofit2.Response
import retrofit2.http.GET

interface TodoAPI {
    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>
}