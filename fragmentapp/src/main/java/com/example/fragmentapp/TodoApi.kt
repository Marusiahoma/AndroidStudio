package com.example.fragmentapp

import retrofit2.Call
import retrofit2.http.GET

interface TodoApi {
    @GET("todos") // Конечная точка API
    fun getTodos(): Call<List<Todo>>
}
