package com.example.mvvm.data.local

data class FavoriteEntity(
    val id: Int = 0,
    val question: String,
    val correctAnswer: String,
    val userAnswer: String,
    val isCorrect: Boolean,
    val category: String
)