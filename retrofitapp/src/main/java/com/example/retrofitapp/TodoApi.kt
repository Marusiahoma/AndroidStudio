package com.example.retrofitapp

import retrofit2.Call
import retrofit2.http.GET

interface TodoApi {
    @GET("/todos") // Путь к списку дел
    fun getTodos(): Call<List<Todo>>
}