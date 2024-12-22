package com.example.fragmentapp

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean,
    val imageUrl: String?
)

