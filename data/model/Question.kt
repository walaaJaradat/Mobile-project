package com.example.mvvm.data.model

data class Question(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val category: String
)